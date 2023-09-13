package com.samseen.resilientnotification.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "bitrix24")
@Data
public class Bitrix24Config {
    private String url;
}
