package ru.usernamedrew.synthetichumancorestarter.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "weyland.audit")
@Getter
@Setter
public class AuditConfig {
    private String mode;
    private String topic;
}
