package org.asad.swaggerui.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Getter
@Setter
@Primary
@Configuration
//@EnableConfigurationProperties
@ConfigurationProperties(prefix = "documentation.swagger")
public class SwaggerServicesConfig {

    List<SwaggerServicesProperties> services;

    @Getter
    @Setter
    @EnableConfigurationProperties
    @ConfigurationProperties(prefix = "documentation.swagger.services")
    public static class SwaggerServicesProperties {

        private String name;
        private String url;
        private String version;

    }
}
