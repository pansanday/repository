package com.pandaria.core.exception;

import com.pandaria.core.HttpCode;

public class UserNotLoginException extends BaseException {

    public UserNotLoginException() {
    }

    public UserNotLoginException(String message) {
        super(message);
    }

    public UserNotLoginException(String message, Exception e) {
        super(message, e);
    }

    protected HttpCode getHttpCode() {
        return HttpCode.UNAUTHORIZED;
    }

}

