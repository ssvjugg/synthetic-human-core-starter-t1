package ru.usernamedrew.synthetichumancorestarter.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.usernamedrew.synthetichumancorestarter.annotations.WeylandWatchingYou;
import ru.usernamedrew.synthetichumancorestarter.api.Command;
import ru.usernamedrew.synthetichumancorestarter.api.CommandProcessor;
import ru.usernamedrew.synthetichumancorestarter.commands.CommandPriority;
import ru.usernamedrew.synthetichumancorestarter.exceptions.QueueOverflowException;

import java.util.concurrent.ExecutorService;

@Service
@AllArgsConstructor
@Slf4j
public class CommandProcessorImpl implements CommandProcessor {
    private final ExecutorService executor;

    @Override
    @WeylandWatchingYou
    public void processCommand(Command command) {
        if (command.getPriority() == CommandPriority.CRITICAL) {
            executeCriticalCommand(command);
        } else {
            executeCommonCommand(command);
        }
    }

    private void executeCriticalCommand(Command command) {
        log.info("Executing critical command {}", command);
    }

    private void executeCommonCommand(Command command) {
        executor.submit(() -> log.info("Executing common command {}", command));
    }
}
