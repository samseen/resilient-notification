package com.samseen.resilientnotification.config;

import com.samseen.resilientnotification.gateway.Provider;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ProviderConfig {
    @Value("${provider}")
    private Provider provider;
}
