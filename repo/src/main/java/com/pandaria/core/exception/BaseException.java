package com.pandaria.core.exception;

import com.pandaria.core.HttpCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;

import java.util.Map;

public abstract class BaseException extends RuntimeException {

    protected Map<String, String> errors;

    public BaseException() {
    }

    public BaseException(Throwable ex) {
        super(ex);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable ex) {
        super(message, ex);
    }

    public void handler(ModelMap modelMap) {
        modelMap.put("code", getHttpCode().value());
        if (StringUtils.isNotBlank(getMessage())) {
            modelMap.put("message", getMessage());
        } else {
            modelMap.put("message", getHttpCode().msg());
        }
        if (errors != null && errors.size() > 0) {
            modelMap.put("data", errors);
        }
        modelMap.put("timestamp", System.currentTimeMillis());
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    protected abstract HttpCode getHttpCode();
}
