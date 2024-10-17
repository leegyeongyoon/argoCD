package com.argo.common.config.exception;

public class ArgoCDException extends Exception{
    public static class InternalServerErrorException extends RuntimeException {
        public InternalServerErrorException(String message) {
            super(message);
        }
    }
}
