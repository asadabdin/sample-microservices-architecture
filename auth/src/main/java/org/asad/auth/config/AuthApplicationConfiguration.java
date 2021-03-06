package org.asad.auth.config;

import lombok.RequiredArgsConstructor;
import org.asad.auth.config.filter.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AuthApplicationConfiguration {

    private final CorsFilter corsFilter;

    @Bean
    public FilterRegistrationBean<CorsFilter> loggingFilter() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(corsFilter);
        return registrationBean;
    }
}
