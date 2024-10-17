package com.argo.common.advice;

import com.argo.common.advice.model.ExceptionResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ExceptionResponseVo handleException(Exception exception) {
        log.error(exception.getMessage());
        return new ExceptionResponseVo(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}
