package com.pandaria.core.config;

import com.pandaria.core.message.MessageResolveService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 加载配置
 */
@PropertySource(value = {"classpath:config/thirdParty.properties", "classpath:i18n/messages*.properties"})
public final class Resources {
    /** 第三方登录配置 */
    public static final ResourceBundle THIRDPARTY = ResourceBundle.getBundle("config/thirdParty");
    /** 国际化信息 */
    //private static final Map<String, ResourceBundle> MESSAGES = new HashMap<String, ResourceBundle>();
    private static MessageResolveService messageResolveService;
    private static final Object MUTEX = new Object();

    /** 国际化信息 */
    public static String getMessage(String key, Object... params) {
        Locale locale = LocaleContextHolder.getLocale();
//        ResourceBundle message = MESSAGES.get(locale.getLanguage());
//        if (message == null) {
//            synchronized (MESSAGES) {
//                message = MESSAGES.get(locale.getLanguage());
//                if (message == null) {
//                    message = ResourceBundle.getBundle("i18n/messages", locale);
//                    MESSAGES.put(locale.getLanguage(), message);
//                }
//            }
//        }
//        if (params != null && params.length > 0) {
//            return String.format(message.getString(key), params);
//        }
//        return message.getString(key);
        
        if (messageResolveService == null) {
            synchronized (MUTEX) {
                if (messageResolveService == null) {
                    WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
                    messageResolveService = (MessageResolveService) wac.getBean("exposedMessageService");
                }
            }
        }
        String msg = messageResolveService.getMessages(locale).get(key);
        if (StringUtils.isBlank(msg)) {
            // 没有配置错误消息，则返回Key
            return key;
        }
        if (params != null && params.length > 0) {
            return String.format(messageResolveService.getMessages(locale).get(key), params);
        }
        return messageResolveService.getMessages(locale).get(key);
    }

    /** 清除国际化信息 */
    public static void flushMessage() {
        //MESSAGES.clear();
    }
}
