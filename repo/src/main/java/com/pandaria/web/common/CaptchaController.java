package com.pandaria.web.common;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.pandaria.core.Assert;
import com.pandaria.core.HttpCode;
import com.pandaria.core.base.BaseController;
import com.pandaria.web.entity.ShortMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "验证码类", description = "图形验证码相关")
@RequestMapping(value = "/code")
public class CaptchaController extends BaseController {

    private static final Logger logger = LogManager.getLogger();

    @Resource
    private Producer captchaProducer;

    // TODO: 方式恶意刷验证码,可以在访问验证码时,带个用户的信息,将用户信息存储在redis中.访问时,先检查该用户信息是否存在
    @ApiOperation(value = "获取图形验证码")
    @GetMapping(value = "/captchaImage")
    public void getCaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Set to expire far in the past.
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = captchaProducer.createText();

        // You can store generated code in cookie
        // Cookie cookie = new Cookie(Constants.KAPTCHA_SESSION_KEY, capText);
        // cookie.setMaxAge(300); // 300秒生存期
        // response.addCookie(cookie); // 将cookie加入response

        // You can also store generated code in session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);

        ServletOutputStream out = response.getOutputStream();

        // write the data out
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }

    @ApiOperation(value = "获取短信验证码")
    @PostMapping("/smsCode")
    public Object smsCodeV2(HttpServletRequest request, ModelMap modelMap, @RequestBody ShortMessage shortMessage) {
        modelMap.clear();

        // 校验手机号格式
        String phoneNo = shortMessage.getPhoneNo();
        Assert.isPhoneNo(phoneNo);

        // 校验消息模板id
        String templateId = shortMessage.getTemplateId();
        Assert.isNotBlank(templateId, "TEMPLATE_ID");

        // 用户id
        String userId = shortMessage.getUserId();
        Assert.isNotBlank(userId, "USER_ID");

        // 校验图形验证码
        String captcha = shortMessage.getCaptcha();
        Assert.isNotBlank(captcha, "CAPTCHA");
        String captchaInServer = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (!captcha.equalsIgnoreCase(captchaInServer)) {
            logger.error("用户输入的验证码为[{}],正确的验证码为[{}]", captcha, captchaInServer);
            return setModelMap(modelMap, HttpCode.WRONG_CAPTCHA);
        }
        // 校验正确后,删除验证码
        request.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);

        // TODO: 1.调用发送短信接口,发送短信验证码; 2.解析发送短信结果,返回
        Map<String, Object> result = new HashMap<>();

        int errcode = Integer.parseInt(result.get("errcode").toString());
        if (errcode == 0) {
            return setSuccessModelMap(modelMap);
        } else {
            return setModelMap(modelMap, errcode, result);
        }
    }

}
