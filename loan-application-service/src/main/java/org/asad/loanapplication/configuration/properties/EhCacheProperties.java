package org.asad.loanapplication.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "org.asad.eh-cache")
public class EhCacheProperties {

    private Integer maxEntriesLocalHeap;
    private Integer expiryIntervalInSeconds;
    private String cacheName;
}
