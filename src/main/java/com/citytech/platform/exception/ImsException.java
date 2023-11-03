package com.citytech.platform.exception;

public class ImsException extends RuntimeException {
    private final ExceptionType exceptionType;

    public ImsException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.exceptionType=exceptionType;
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

}

