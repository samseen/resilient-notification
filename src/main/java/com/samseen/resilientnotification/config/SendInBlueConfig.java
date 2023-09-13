package com.samseen.resilientnotification.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sendinblue")
@Data
public class SendInBlueConfig {
    private String url;
}
