package com.argo.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;

@Slf4j
@Component
public class UrlMasker {

    public String maskAllUrl(String originalString) {
        String ipv4Pattern = "(\\b25[0-5]\\b|\\b2[0-4][0-9]\\b|\\b1[0-9]{2}\\b|\\b[1-9]?[0-9]\\b)\\."
                + "(\\b25[0-5]\\b|\\b2[0-4][0-9]\\b|\\b1[0-9]{2}\\b|\\b[1-9]?[0-9]\\b)\\."
                + "(\\b25[0-5]\\b|\\b2[0-4][0-9]\\b|\\b1[0-9]{2}\\b|\\b[1-9]?[0-9]\\b)\\."
                + "(\\b25[0-5]\\b|\\b2[0-4][0-9]\\b|\\b1[0-9]{2}\\b|\\b[1-9]?[0-9]\\b)";
        return originalString.replace(ipv4Pattern, "x.x.x.x");
    }

    /**
     * 전달 받은 {@code Throwable} 객체의 {@code String}을 인자로 하는 생성자를 사용하여 리턴 객체를 생성합니다.
     * 해당 생성자가 존재하지 않을경우 {@code Throwable}의 생성자를 사용하여 리턴 객체를 생성합니다.
     */
    public <T extends Throwable> Throwable maskAllUrl(T throwable) {
        StackTraceElement[] originalStackTrace = throwable.getStackTrace();
        StackTraceElement[] maskedStackTrace = new StackTraceElement[originalStackTrace.length];
        String maskedMessage = maskAllUrl(throwable.getMessage());

        for (int i = 0; i < originalStackTrace.length; i++) {
            String className = maskAllUrl(originalStackTrace[i].getClassName());
            String methodName = maskAllUrl(originalStackTrace[i].getMethodName());
            String fileName = originalStackTrace[i].getFileName();
            int lineNumber = originalStackTrace[i].getLineNumber();

            maskedStackTrace[i] = new StackTraceElement(className, methodName, fileName, lineNumber);
        }

        Throwable maskedThrowable = createThrowableInstance(throwable, maskedMessage);
        maskedThrowable.setStackTrace(maskedStackTrace);

        for (Throwable suppressed : throwable.getSuppressed()) {
            maskedThrowable.addSuppressed(maskAllUrl(suppressed));
        }

        Throwable cause = throwable.getCause();
        if (cause != null && cause != throwable) {
            maskedThrowable.initCause(maskAllUrl(cause));
        }

        return maskedThrowable;
    }

    private <T extends Throwable> Throwable createThrowableInstance(T original, String message) {
        try{
            Constructor<?> constructor = original.getClass().getConstructor(String.class);
            return (Throwable) constructor.newInstance(message);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            return new Throwable(message);
        }
    }
}
