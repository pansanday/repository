package com.pandaria.core.message;

import org.springframework.context.MessageSourceAware;

import java.util.Locale;
import java.util.Map;

public interface MessageResolveService extends MessageSourceAware {

    String getMessage(String key, Object[] argumentsForKey, Locale locale);

    Map<String, String> getMessages(Locale locale);
}