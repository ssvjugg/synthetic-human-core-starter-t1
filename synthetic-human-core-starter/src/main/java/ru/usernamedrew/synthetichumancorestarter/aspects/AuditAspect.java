package ru.usernamedrew.synthetichumancorestarter.aspects;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.usernamedrew.synthetichumancorestarter.config.AuditConfig;

import java.util.Arrays;

@Aspect
@Component
@AllArgsConstructor
@Slf4j
public class AuditAspect {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final AuditConfig auditConfig;

    @Pointcut("@annotation(ru.usernamedrew.synthetichumancorestarter.annotations.WeylandWatchingYou)")
    public void anyWeylandWatchingYouAnnotatedMethods() {}

    @Around("anyWeylandWatchingYouAnnotatedMethods()")
    public Object aroundAudit(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        Object result = joinPoint.proceed();

        String auditLog = String.format("Class: %s\n Method: %s\n Arguments: %s\n Result: %s\n", className, methodName, Arrays.toString(args), result);

        if ("CONSOLE".equals(auditConfig.getMode())) {
            log.info(auditLog);
        } else {
            kafkaTemplate.send(auditConfig.getTopic(), auditLog);
        }

        return result;
    }
}
