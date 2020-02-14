package org.asad.customer.configuration;

import feign.Logger;
import lombok.RequiredArgsConstructor;
import org.asad.customer.configuration.filter.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CustomerConfiguration {

    private final CorsFilter corsFilter;

    @Bean
    public FilterRegistrationBean<CorsFilter> loggingFilter() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(corsFilter);
        return registrationBean;
    }

    @Bean
    public Logger.Level configureLogLevel() {
        return Logger.Level.FULL;
    }
}
