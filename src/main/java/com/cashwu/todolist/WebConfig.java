package com.cashwu.todolist;

import com.cashwu.todolist.service.VideoService;
import com.cashwu.todolist.service.VimeoService;
import com.cashwu.todolist.service.YoutubeService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
// 重點在於導入其它的 package ，加入容器用
@EnableConfigurationProperties(MyProperties.class)
public class WebConfig implements WebMvcConfigurer {

    /**
     * 註冊locale解析器bean
     */
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.TAIWAN);
        return localeResolver;
    }

    /**
     * 註冊locale攔截器bean
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        // use request param "lang" to change locale setting
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    /**
     * 註冊locale截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Bean
    @ConditionalOnProperty(prefix = "my.app", name = "video", havingValue = "youtube")
    public VideoService youtubeService() {
        return new YoutubeService();
    }

    @Bean
    @ConditionalOnProperty(prefix = "my.app", name = "video", havingValue = "vimeo")
    public VideoService vimeoService() {
        return new VimeoService();
    }
}
