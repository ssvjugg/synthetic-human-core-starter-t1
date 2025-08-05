package ru.usernamedrew.synthetichumancorestarter.aspects.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.usernamedrew.synthetichumancorestarter.api.AuditService;
import ru.usernamedrew.synthetichumancorestarter.aspects.models.AuditEvent;

@Service
@Slf4j
public class ConsoleAuditService implements AuditService {

    @Override
    public void audit(AuditEvent event) {
        log.info("""
                Audit event:
                \tMethod - {}
                \tArguments - {}
                \tExecutionState - {}
                \tTimestamp - {}
                """,
                event.getMethod(),
                event.getArguments(),
                event.getExecutionStatus(),
                event.getTimestamp()
        );
    }
}
