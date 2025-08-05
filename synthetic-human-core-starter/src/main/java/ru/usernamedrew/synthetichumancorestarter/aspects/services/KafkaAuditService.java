package ru.usernamedrew.synthetichumancorestarter.aspects.services;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.usernamedrew.synthetichumancorestarter.api.AuditService;
import ru.usernamedrew.synthetichumancorestarter.aspects.models.AuditEvent;
import ru.usernamedrew.synthetichumancorestarter.config.AuditConfig;

@Service
@ConditionalOnProperty(name = "audit.properties.weyland.audit.mode", havingValue = "KAFKA")
@AllArgsConstructor
public class KafkaAuditService implements AuditService {

    private final KafkaTemplate<String, AuditEvent> kafkaTemplate;
    private final AuditConfig auditConfig;

    @Override
    public void audit(AuditEvent event) {
        kafkaTemplate.send(auditConfig.getTopic(), event);
    }
}
