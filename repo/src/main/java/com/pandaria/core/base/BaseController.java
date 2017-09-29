package com.pandaria.core.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.pandaria.core.Constants;
import com.pandaria.core.HttpCode;
import com.pandaria.core.config.Resources;
import com.pandaria.core.exception.BaseException;
import com.pandaria.core.exception.IllegalParameterException;
import com.pandaria.core.validator.BeanValidators;
import com.pandaria.util.WebUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

/**
 * BeanValidators
 */
public abstract class BaseController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    protected Validator validator;

    /**
     * 获取当前用户Id
     */
    protected String getCurrUser() {
        return WebUtil.getCurrentUser();
    }

    /**
     * 设置成功响应代码
     */
    protected ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap) {
        return setSuccessModelMap(modelMap, null);
    }

    /**
     * 设置成功响应代码
     */
    private ResponseEntity<ModelMap> setSuccessModelMap(ModelMap modelMap, Object data) {
        return setModelMap(modelMap, HttpCode.OK, data);
    }

    /**
     * 设置重定向相应代码
     */
    protected ResponseEntity<ModelMap> setRedirectModelMap(ModelMap modelMap, String redirectUrl) {
        modelMap.put("needRedirect", true);
        modelMap.put("redirectUrl", redirectUrl);
        return setModelMap(modelMap, HttpCode.OK, null);
    }

    /**
     * 设置响应代码
     */
    protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code) {
        return setModelMap(modelMap, code, null);
    }

    /**
     * 设置响应代码
     */
    private ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, HttpCode code, Object data) {
        modelMap.remove("void");
        if (data != null) {
            modelMap.put("data", data);
        }
        modelMap.put("code", code.value());
        modelMap.put("message", code.msg());
        modelMap.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(modelMap);
    }

    /**
     * 适用范围:仅知道动态的错误码,不知道具体错误原因.如:调用微信接口,微信返回了错误码.
     * 尽量采用setModelMap(ModelMap modelMap, HttpCode code, Object data)方法
     *
     * @param modelMap
     * @param code
     * @param data
     * @return
     */
    protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, int code, Object data) {
        modelMap.remove("void");
        if (data != null) {
            modelMap.put("data", data);
        }
        modelMap.put("code", code);
        modelMap.put("message", Resources.getMessage("HTTPCODE_" + code));
        modelMap.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(modelMap);
    }

    protected ResponseEntity<ModelMap> setModelMap(ModelMap modelMap, Exception e) {
        modelMap.remove("void");
        modelMap.put("code", HttpCode.INTERNAL_SERVER_ERROR.value());
        modelMap.put("message", e.getMessage());
        modelMap.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(modelMap);
    }

    /**
     * 异常处理
     */
    @ExceptionHandler(Exception.class)
    public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex)
            throws Exception {
        logger.error(Constants.Exception_Head, ex);
        ModelMap modelMap = new ModelMap();
        if (ex instanceof IllegalParameterException) {
            ((IllegalParameterException) ex).handler(modelMap);
        } else if (ex instanceof BaseException) {
            ((BaseException) ex).handler(modelMap);
        } else if (ex instanceof IllegalArgumentException) {
            new IllegalParameterException(ex.getMessage()).handler(modelMap);
        } else if (ex instanceof UnauthorizedException) {
            modelMap.put("code", HttpCode.FORBIDDEN.value());
            modelMap.put("message", HttpCode.FORBIDDEN.msg());
            //modelMap.put("message", StringUtils.defaultIfBlank(ex.getMessage(), HttpCode.FORBIDDEN.msg()));
        } else if (ex instanceof BindException) {
            // spring bind exception, like @DateTimeFormat
            new IllegalParameterException(BeanValidators.extractBindErrors((BindException) ex)).handler(modelMap);
        } else if (ex instanceof NullPointerException) {
            modelMap.put("code", HttpCode.NOT_FOUND.value());
            modelMap.put("message", StringUtils.defaultIfBlank(ex.getMessage(), HttpCode.NOT_FOUND.msg()));
        } else if (ex.getMessage().contains("DuplicateKeyException")) {
            modelMap.put("code", HttpCode.CONFLICT.value());
            modelMap.put("message", HttpCode.CONFLICT.msg());
        } else {
            modelMap.put("code", HttpCode.INTERNAL_SERVER_ERROR.value());
            String msg = StringUtils.defaultIfBlank(ex.getMessage(), HttpCode.INTERNAL_SERVER_ERROR.msg());
            modelMap.put("message", msg.length() > 100 ? "系统走神了,请稍候再试." : msg);
        }
        response.setContentType("application/json;charset=UTF-8");
        modelMap.put("timestamp", System.currentTimeMillis());
        logger.info(JSON.toJSON(modelMap));
        byte[] bytes = JSON.toJSONBytes(modelMap, SerializerFeature.DisableCircularReferenceDetect);
        response.getOutputStream().write(bytes);
    }

    /**
     * 服务端参数有效性验证
     *
     * @param modelMap
     * @param object   验证的实体对象
     * @param groups   验证组
     * @since 2016年10月13日
     */
    protected void beanValidator(ModelMap modelMap, Object object, Class<?>... groups) {
        try {
            BeanValidators.validateWithException(validator, object, groups);
        } catch (ConstraintViolationException ex) {
            logger.error("modelMap=[{}], object=[{}], groups=[{}], ex[{}]", modelMap, object, groups, ex);
            throw new IllegalParameterException(BeanValidators.extractPropertyAndMessage(ex));
        } catch (BindException be) {
            logger.error("modelMap=[{}], object=[{}], groups=[{}], be[{}]", modelMap, object, groups, be);
            throw new IllegalParameterException(BeanValidators.extractBindErrors(be));
        }
    }
}
