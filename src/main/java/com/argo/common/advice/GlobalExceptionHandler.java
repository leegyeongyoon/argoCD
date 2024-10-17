package com.argo.common.advice;

import com.argo.common.advice.model.ExceptionResponseVo;
import com.argo.common.util.UrlMasker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private final UrlMasker urlMasker;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionResponseVo handleException(Exception exception) {
        Throwable masked = urlMasker.maskAllUrl(exception); // webclient 에러에서 요청 url 경로 마스킹
        log.error(exception.getMessage(), exception);
        return new ExceptionResponseVo(HttpStatus.INTERNAL_SERVER_ERROR, masked.getMessage());
    }
}
