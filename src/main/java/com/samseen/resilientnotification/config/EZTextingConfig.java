package com.samseen.resilientnotification.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "eztexting")
@Data
public class EZTextingConfig {
    private String url;
}
