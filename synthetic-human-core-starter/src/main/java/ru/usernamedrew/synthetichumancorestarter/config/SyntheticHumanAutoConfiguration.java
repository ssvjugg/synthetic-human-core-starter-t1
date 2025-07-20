package ru.usernamedrew.synthetichumancorestarter.config;

import jakarta.validation.Validator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import ru.usernamedrew.synthetichumancorestarter.api.CommandProcessor;
import ru.usernamedrew.synthetichumancorestarter.aspects.AuditAspect;
import ru.usernamedrew.synthetichumancorestarter.services.CommandProcessorImpl;

import java.util.concurrent.ExecutorService;

@Configuration
public class SyntheticHumanAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public CommandProcessor commandProcessor(ExecutorService executorService, Validator validator) {
        return new CommandProcessorImpl(executorService, validator);
    }

    @Bean
    @ConditionalOnMissingBean
    public AuditConfig auditConfig() {
        return new AuditConfig();
    }

    @Bean
    @ConditionalOnMissingBean
    public AuditAspect  auditAspect(KafkaTemplate<String, String> kafkaTemplate, AuditConfig auditConfig) {
        return new AuditAspect(kafkaTemplate, auditConfig);
    }
}
