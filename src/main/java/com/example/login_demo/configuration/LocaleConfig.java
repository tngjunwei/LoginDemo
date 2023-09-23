package com.example.login_demo.configuration;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class LocaleConfig implements WebMvcConfigurer {

	@Bean
	public LocaleResolver localeResolver() {
		var slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		slr.setLocaleAttributeName("session.current.locale");
	    slr.setTimeZoneAttributeName("session.current.timezone");
	    return slr;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		var lci = new LocaleChangeInterceptor();
		lci.setParamName("language");
		return lci;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry reg) {
		reg.addInterceptor(localeChangeInterceptor());
	}
	
	@Bean("messageSource")
	public MessageSource messageSource() {
		var src = new ResourceBundleMessageSource();
		src.setBasename("language/messages");
		src.setDefaultEncoding("UTF-8");
		return src;
	}
}
