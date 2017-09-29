package com.pandaria.core.exception;

import com.pandaria.core.HttpCode;

import java.util.Map;

public class IllegalParameterException extends BaseException {

    public IllegalParameterException() {
    }

    public IllegalParameterException(Map<String, String> errors) {
        this.errors = errors;
    }

    public IllegalParameterException(Throwable ex) {
        super(ex);
    }

    public IllegalParameterException(String message) {
        super(message);
    }

    public IllegalParameterException(String message, Throwable ex) {
        super(message, ex);
    }

    protected HttpCode getHttpCode() {
        return HttpCode.BAD_REQUEST;
    }
}
