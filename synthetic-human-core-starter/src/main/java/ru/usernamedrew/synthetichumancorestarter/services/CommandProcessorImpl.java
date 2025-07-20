package ru.usernamedrew.synthetichumancorestarter.services;

import jakarta.annotation.PreDestroy;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.usernamedrew.synthetichumancorestarter.annotations.WeylandWatchingYou;
import ru.usernamedrew.synthetichumancorestarter.api.Command;
import ru.usernamedrew.synthetichumancorestarter.api.CommandProcessor;
import ru.usernamedrew.synthetichumancorestarter.commands.CommandPriority;
import ru.usernamedrew.synthetichumancorestarter.exceptions.CommandValidationException;
import ru.usernamedrew.synthetichumancorestarter.exceptions.QueueOverflowException;
import ru.usernamedrew.synthetichumancorestarter.monitoring.CommandMetrics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

@Service
@AllArgsConstructor
@Slf4j
public class CommandProcessorImpl implements CommandProcessor {
    private final ExecutorService executor;
    private final CommandMetrics commandMetrics;
    private final Validator validator;

    @Override
    @WeylandWatchingYou
    public void processCommand(Command command) {
        if (!validator.validate(command).isEmpty()) {
            throw new CommandValidationException("Invalid command");
        }

        if (command.getPriority() == CommandPriority.CRITICAL) {
            executeCriticalCommand(command);
        } else {
            executeCommonCommand(command);
        }

        commandMetrics.incrementAuthorCommandCount(command.getAuthor());
    }

    // Обработка критических команд
    private void executeCriticalCommand(Command command) {
        log.info("Executing critical command {}", command);
    }

    // Обработка обычных команд
    private void executeCommonCommand(Command command) {
        try {
            executor.submit(() -> log.info("Executing common command {}", command));
        } catch(RejectedExecutionException e) {
            throw new QueueOverflowException(e.getMessage());
        }
    }

    @PreDestroy
    public void shutdown() {
        executor.shutdownNow();
    }
}
