package com.example.imdb.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<RequestCounterFilter> loggingFilter() {
        FilterRegistrationBean<RequestCounterFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestCounterFilter());
        registrationBean.addUrlPatterns("/imdb/*");
        return registrationBean;
    }
}
