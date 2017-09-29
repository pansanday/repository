package com.pandaria.core.message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

@Service(value = "exposedMessageService")
public class MessageResolverServiceImpl implements MessageResolveService {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    private MessageSource messageSource;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        logger.info("Messages i18n injected");
        this.messageSource = messageSource;
    }

    public String getMessage(String key, Object[] arguments, Locale locale) {
        String message = "";
        try {
            message = messageSource.getMessage(key, arguments, locale);
        } catch (NoSuchMessageException e) {
            message = key;
            logger.warn("No message found: " + key);
        }
        return message;
    }

    public Map<String, String> getMessages(Locale locale) {
        Properties properties = ((XmlWebApplicationContext) messageSource).getBean("messageSource",
                ExposedResourceMessageBundleSource.class).getMessages(locale);
        Map<String, String> messagesMap = new HashMap<String, String>();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                messagesMap.put(entry.getKey().toString(), entry.getValue().toString());
            }
        }
        return messagesMap;
    }

}

