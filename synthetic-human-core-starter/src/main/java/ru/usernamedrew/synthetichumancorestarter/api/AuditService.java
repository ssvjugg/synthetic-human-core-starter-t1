package ru.usernamedrew.synthetichumancorestarter.api;

import ru.usernamedrew.synthetichumancorestarter.aspects.models.AuditEvent;

public interface AuditService {

    void audit(AuditEvent event);

}
