package com.pandaria.web.end;

import com.pandaria.learn.javaxval.Member;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Controller
@ResponseBody
@Api(description = "对象属性校验")
@RequestMapping(value = "/member")
public class MemberController {

    private static final Logger logger = LogManager.getLogger();

    @ApiOperation(value = "提交校验")
    @PostMapping(value = "/submit")
    public Object submitForm(@ModelAttribute("members") Member member, BindingResult result) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Member>> violations = validator.validate(member);
        for (ConstraintViolation<Member> violation : violations) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            result.addError(new FieldError("member", propertyPath, "Invalid " + propertyPath + "(" + message + ")"));
        }

        if (result.hasErrors()) {
            logger.error("Something goes wrong!");
            logger.info(result);
        } else {
            logger.info("Nothing wrong, congratulations!");
        }

        return member;
    }
}
