package com.intro.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.intro.api.interceptor.LoginRequiredInterceptor;

@Component
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    LoginRequiredInterceptor loginRequiredInterceptor;

    @Bean
    public WebConfiguration getWebConfig() {
        WebConfiguration config = new WebConfiguration() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
                registry.addResourceHandler("/webjars/**")
                        .addResourceLocations("classpath:/META-INF/resources/webjars/");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(loginRequiredInterceptor).addPathPatterns("/**")
                        .excludePathPatterns("/swagger-ui.html", "/swagger-resources/**", "/webjars/**")
                        .excludePathPatterns("/v2/api-docs", "/error", "/account/login", "/account/register");
            }
        };
        return config;
    }
}
