package ru.usernamedrew.synthetichumancorestarter;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.validation.Validator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;
import ru.usernamedrew.synthetichumancorestarter.api.AuditService;
import ru.usernamedrew.synthetichumancorestarter.api.CommandProcessor;
import ru.usernamedrew.synthetichumancorestarter.aspects.AuditAspect;
import ru.usernamedrew.synthetichumancorestarter.config.AuditConfig;
import ru.usernamedrew.synthetichumancorestarter.monitoring.CommandMetrics;
import ru.usernamedrew.synthetichumancorestarter.services.CommandProcessorImpl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

@Configuration
@ComponentScan
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
    public AuditAspect  auditAspect(AuditService auditService) {
        return new AuditAspect(auditService);
    }
}
