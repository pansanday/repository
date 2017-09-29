package com.pandaria.core;

import com.pandaria.core.config.Resources;

/**
 * Ajax 请求时的自定义查询状态码，主要参考Http状态码，但并不完全对应
 */
public enum HttpCode {
    /**
     * 200请求成功
     */
    OK(200),
    /**
     * 207频繁操作
     */
    MULTI_STATUS(207),
    /**
     * 303登录失败
     */
    LOGIN_FAIL(303),
    /**
     * 400请求参数出错
     */
    BAD_REQUEST(400),
    /**
     * 401没有登录
     */
    UNAUTHORIZED(401),
    /**
     * 403没有权限
     */
    FORBIDDEN(403),
    /**
     * 404找不到页面
     */
    NOT_FOUND(404),
    /**
     * 408请求超时
     */
    REQUEST_TIMEOUT(408),
    /**
     * 409发生冲突
     */
    CONFLICT(409),
    /**
     * 410已被删除
     */
    GONE(410),
    /**
     * 423已被锁定
     */
    LOCKED(423),
    /**
     * 429请求太频繁
     */
    TOO_MANY_REQUESTS(429),
    /**
     * 500服务器出错
     */
    INTERNAL_SERVER_ERROR(500),
    /**
     * 507服务器无法存储完成请求所必须的内容
     */
    INSUFFICIENT_STORAGE(507),

    /**
     * 2001验证码错误
     */
    WRONG_CAPTCHA(2001),
    /**
     * 2002密码错误
     */
    WRONG_PASSWORD(2002),
    /**
     * 2010短信码错误
     */
    WRONG_SMSCODE(2010),
    /**
     * 2011用户类型错误
     */
    WRONG_USERTYPE(2011),
    /**
     * 2020用户已经存在
     */
    USER_ALREADY_EXISTS(2020),
    /**
     * 2021当前用户不存在
     */
    USER_ACCOUNT_NOTEXIST(2021),
    ;

    private final Integer value;

    private HttpCode(Integer value) {
        this.value = value;
    }

    /**
     * Return the integer value of this status code.
     */
    public Integer value() {
        return this.value;
    }

    public String msg() {
        return Resources.getMessage("HTTPCODE_" + this.value);
    }

    public String toString() {
        return this.value.toString();
    }
}
