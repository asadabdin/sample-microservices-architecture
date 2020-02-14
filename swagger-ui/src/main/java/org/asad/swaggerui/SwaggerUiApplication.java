package org.asad.swaggerui;

import org.asad.swaggerui.configuration.properties.SwaggerServicesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableConfigurationProperties({SwaggerServicesConfig.class, SwaggerServicesConfig.SwaggerServicesProperties.class})
public class SwaggerUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerUiApplication.class, args);
    }
}
