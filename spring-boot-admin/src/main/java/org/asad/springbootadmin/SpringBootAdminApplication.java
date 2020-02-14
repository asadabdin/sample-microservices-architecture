package org.asad.springbootadmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
public class SpringBootAdminApplication {

    public static void main(String[] args) {
        //SpringApplication.run(AdminServiceApplication.class, args);
        new SpringApplicationBuilder(SpringBootAdminApplication.class)
                .web(WebApplicationType.REACTIVE)
                .run(args);
    }
}
