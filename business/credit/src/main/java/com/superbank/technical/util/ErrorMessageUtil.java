package com.superbank.technical.util;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

public final class ErrorMessageUtil {
    private static final MessageSourceAccessor ACCESSOR;

    private ErrorMessageUtil(){
        throw new UnsupportedOperationException("Class " + ErrorMessageUtil.class + " cannot be initialized by constructor");
    }

    static {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:errorMessages");
        messageSource.setDefaultEncoding("UTF-8");
        ACCESSOR = new MessageSourceAccessor(messageSource, Locale.US);
    }

    public static String get(final String code) {
        return ACCESSOR.getMessage(code);
    }

    public static String get(final String code, final Object... args) {
        return ACCESSOR.getMessage(code, args);
    }
}
