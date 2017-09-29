package com.pandaria.web.entity;

/**
 * 短信类
 */
public class ShortMessage {

    /**
     * 图形验证码
     */
    private String captcha;
    /**
     * 版本号
     */
    private String version;
    /**
     * 应用ID
     */
    private String appid;
    /**
     * 签名
     */
    private String sign;
    /**
     * 签名方式( 01:SHA1 02:MD5 )
     */
    private String signMethod;
    /**
     * 密钥id
     */
    private String secretId;
    /**
     * 时间戳
     */
    private String timestamp;
    /**
     * 发送手机号码
     */
    private String phoneNo;
    /**
     * 发送短信内容,需要对该字段进行URLEncoder
     */
    private String content;
    /**
     * 当前用户账号
     */
    private String userId;
    /**
     * 调用渠道
     */
    private String channel;
    /**
     * 短信模板id
     */
    private String templateId;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSignMethod() {
        return signMethod;
    }

    public void setSignMethod(String signMethod) {
        this.signMethod = signMethod;
    }

    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}
