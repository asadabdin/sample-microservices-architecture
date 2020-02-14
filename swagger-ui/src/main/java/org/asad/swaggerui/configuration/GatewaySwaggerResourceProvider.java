package org.asad.swaggerui.configuration;

import lombok.RequiredArgsConstructor;
import org.asad.swaggerui.configuration.properties.SwaggerServicesConfig;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Component
@RequiredArgsConstructor
public class GatewaySwaggerResourceProvider implements SwaggerResourcesProvider {

    private final SwaggerServicesConfig swaggerServiceList;

    @Override
    public List<SwaggerResource> get() {
        return swaggerServiceList.getServices().stream()
                .map(service -> swaggerResource(service.getName(), service.getUrl(), service.getVersion()))
                .collect(Collectors.toList());
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

}
