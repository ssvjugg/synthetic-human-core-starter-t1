package ru.usernamedrew.synthetichumancorestarter.config;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.validation.Validator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import ru.usernamedrew.synthetichumancorestarter.api.CommandProcessor;
import ru.usernamedrew.synthetichumancorestarter.aspects.AuditAspect;
import ru.usernamedrew.synthetichumancorestarter.monitoring.CommandMetrics;
import ru.usernamedrew.synthetichumancorestarter.services.CommandProcessorImpl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

@Configuration
@Import({CommandProcessorImplConfig.class})
@EnableAspectJAutoProxy
public class SyntheticHumanAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public CommandMetrics commandMetrics(BlockingQueue<Runnable> commandQueue, MeterRegistry meterRegistry) {
        return new CommandMetrics(meterRegistry, commandQueue);
    }

    @Bean
    @ConditionalOnMissingBean
    public CommandProcessor commandProcessor(ExecutorService executorService, Validator validator, CommandMetrics commandMetrics) {
        return new CommandProcessorImpl(executorService, commandMetrics, validator);
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
