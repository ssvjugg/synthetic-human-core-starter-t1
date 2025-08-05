package ru.usernamedrew.synthetichumancorestarter.aspects.models;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class AuditEvent {
    private String method;
    private String arguments;
    private ExecutionState executionStatus;
    private LocalDateTime timestamp;

    public enum ExecutionState {
        SUCCESS,
        EXECEPTION
    }
}
