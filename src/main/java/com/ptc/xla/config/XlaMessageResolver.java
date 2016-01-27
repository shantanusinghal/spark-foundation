package com.ptc.xla.config;

import org.thymeleaf.Arguments;
import org.thymeleaf.cache.ICache;
import org.thymeleaf.cache.ICacheManager;
import org.thymeleaf.messageresolver.AbstractMessageResolver;
import org.thymeleaf.messageresolver.IMessageResolver;
import org.thymeleaf.messageresolver.MessageResolution;
import org.thymeleaf.resourceresolver.IResourceResolver;
import org.thymeleaf.util.MessageResolutionUtils;
import org.thymeleaf.util.Validate;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;

/**
 * Created by ssinghal
 * Created on 27-Jan-2016
 */
public class XlaMessageResolver extends AbstractMessageResolver implements IMessageResolver {

    public static final Properties DEFAULT_PROP = new Properties();
    private static final Object[] EMPTY_MESSAGE_PARAMETERS = new Object[0];
    private static final String DEFAULT_TEMPLATE_BASE = "i18n/i18n";

    @Override
    public MessageResolution resolveMessage(Arguments arguments, String key, Object[] messageParameters) {
        this.checkInitialized();
        String message = resolveMessageForTemplate(arguments, key, messageParameters);
        return message == null?null:new MessageResolution(message);
    }

    private String resolveMessageForTemplate(Arguments arguments, String key, Object[] messageParameters) {
        Validate.notNull(arguments, "Arguments cannot be null");
        Validate.notNull(arguments.getContext().getLocale(), "Locale in context cannot be null");
        Validate.notNull(key, "Message key cannot be null");
        Locale locale = arguments.getContext().getLocale();
        String templateName = arguments.getTemplateResolution().getTemplateName();
        String cacheKey = "{template_msg}" + templateName + '_' + locale.toString();
        Properties properties = null;
        ICache messagesCache = null;
        ICacheManager cacheManager = arguments.getConfiguration().getCacheManager();
        if(cacheManager != null) {
            messagesCache = cacheManager.getMessageCache();
            if(messagesCache != null) {
                properties = (Properties)messagesCache.get(cacheKey);
            }
        }

        if(properties == null) {
            properties = loadMessagesForTemplate(arguments);
            if(messagesCache != null) {
                messagesCache.put(cacheKey, properties);
            }
        }

        String messageValue = properties.getProperty(key);
        if(messageValue == null) {
            return null;
        } else {
            MessageFormat messageFormat = new MessageFormat(messageValue, locale);
            return messageFormat.format(messageParameters != null ? messageParameters : EMPTY_MESSAGE_PARAMETERS);
        }
    }

    private static Properties loadMessagesForTemplate(Arguments arguments) {
        Validate.notNull(arguments, "Arguments cannot be null");
        Validate.notNull(arguments.getContext().getLocale(), "Locale in context cannot be null");
        IResourceResolver resourceResolver = arguments.getTemplateResolution().getResourceResolver();
        Locale locale = arguments.getContext().getLocale();
        return MessageResolutionUtils.loadCombinedMessagesFilesFromBaseName(arguments, resourceResolver, DEFAULT_TEMPLATE_BASE, locale, DEFAULT_PROP);
    }

}
