package com.pandaria.core.message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

@Component
public class ExposedResourceMessageBundleSource extends ReloadableResourceBundleMessageSource {
    protected final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected Properties loadProperties(Resource resource, String fileName) throws IOException {
        logger.info("Load " + fileName);
        return super.loadProperties(resource, fileName);
    }

    /**
     * Gets all messages for presented Locale.
     * @param locale user request's locale
     * @return all messages
     */
    public Properties getMessages(Locale locale){
        return getMergedProperties(locale).getProperties();
    }
}

