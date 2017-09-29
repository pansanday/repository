package com.pandaria.core.validator;

import com.pandaria.core.config.Resources;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;

/**
 * JSR303 Validator(Hibernate Validator)工具类.
 * <p>
 * ConstraintViolation中包含propertyPath, message 和invalidValue等信息.
 * 提供了各种convert方法，适合不同的i18n需求:
 * 1. List<String>, String内容为message
 * 2. List<String>, String内容为propertyPath + separator + message
 * 3. Map<propertyPath, message>
 */
public class BeanValidators {

    private static final Validator javaxValidator = Validation.buildDefaultValidatorFactory().getValidator();
    private static final SpringValidatorAdapter springValidator = new SpringValidatorAdapter(javaxValidator);

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void validateWithException(Validator validator, Object object, Class<?>... groups)
            throws ConstraintViolationException, BindException {
        // JSR303, JSR349 validate
        Set constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        // spring bind validate
        BindingResult errors = new BeanPropertyBindingResult(object, object.getClass().getName());
        springValidator.validate(object, errors);
        if (errors == null || errors.getAllErrors().isEmpty()) {
            return;
        } else {
            throw new BindException(errors);
        }
    }

    public static List<String> extractMessage(ConstraintViolationException e) {
        return extractMessage(e.getConstraintViolations());
    }

    @SuppressWarnings("rawtypes")
    public static List<String> extractMessage(Set<? extends ConstraintViolation> constraintViolations) {
        List<String> errorMessages = new ArrayList<String>();
        for (ConstraintViolation violation : constraintViolations) {
            errorMessages.add(violation.getMessage());
        }
        return errorMessages;
    }

    public static Map<String, String> extractPropertyAndMessage(ConstraintViolationException e) {
        return extractPropertyAndMessage(e.getConstraintViolations());
    }

    public static Map<String, String> extractBindErrors(BindException be) {
        Map<String, String> errorMessages = new HashMap<String, String>();
        List<FieldError> fieldErrors = be.getFieldErrors();
        for (FieldError fe : fieldErrors) {
            errorMessages.put(fe.getField(), Resources.getMessage(fe.getCode(), fe.getArguments())/*+fe.getDefaultMessage()*/);
        }
        return errorMessages;
    }

    @SuppressWarnings("rawtypes")
    public static Map<String, String> extractPropertyAndMessage(Set<? extends ConstraintViolation> constraintViolations) {
        Map<String, String> errorMessages = new HashMap<String, String>();
        for (ConstraintViolation violation : constraintViolations) {
            errorMessages.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return errorMessages;
    }

    public static List<String> extractPropertyAndMessageAsList(ConstraintViolationException e) {
        return extractPropertyAndMessageAsList(e.getConstraintViolations(), ", ");
    }

    @SuppressWarnings("rawtypes")
    public static List<String> extractPropertyAndMessageAsList(Set<? extends ConstraintViolation> constraintViolations) {
        return extractPropertyAndMessageAsList(constraintViolations, ", ");
    }

    public static List<String> extractPropertyAndMessageAsList(ConstraintViolationException e, String separator) {
        return extractPropertyAndMessageAsList(e.getConstraintViolations(), separator);
    }

    @SuppressWarnings("rawtypes")
    public static List<String> extractPropertyAndMessageAsList(Set<? extends ConstraintViolation> constraintViolations,
                                                               String separator) {
        List<String> errorMessages = new ArrayList<String>();
        for (ConstraintViolation violation : constraintViolations) {
            errorMessages.add(violation.getPropertyPath() + separator + Resources.getMessage(violation.getMessage()));
        }
        return errorMessages;
    }
}