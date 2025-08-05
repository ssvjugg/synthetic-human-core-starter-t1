package ru.usernamedrew.synthetichumancorestarter.aspects;

import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.usernamedrew.synthetichumancorestarter.api.AuditService;
import ru.usernamedrew.synthetichumancorestarter.aspects.models.AuditEvent;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@AllArgsConstructor
public class AuditAspect {
    private final AuditService auditService;

    @Pointcut("@annotation(ru.usernamedrew.synthetichumancorestarter.annotations.WeylandWatchingYou)")
    public void anyWeylandWatchingYouAnnotatedMethods() {}

    @Around("anyWeylandWatchingYouAnnotatedMethods()")
    public Object aroundAudit(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        var builder = AuditEvent.builder()
                .method(methodName)
                .arguments(Arrays.toString(args))
                .timestamp(LocalDateTime.now());

        try {
            Object result = joinPoint.proceed();
            builder.executionStatus(AuditEvent.ExecutionState.SUCCESS);
            return result;
        } catch (Throwable throwable) {
            builder.executionStatus(AuditEvent.ExecutionState.EXECEPTION);
            throw throwable;
        } finally {
            auditService.audit(builder.build());
        }
    }
}
