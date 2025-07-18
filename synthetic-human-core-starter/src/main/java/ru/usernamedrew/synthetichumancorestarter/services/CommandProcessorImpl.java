package ru.usernamedrew.synthetichumancorestarter.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.usernamedrew.synthetichumancorestarter.api.Command;
import ru.usernamedrew.synthetichumancorestarter.api.CommandProcessor;
import ru.usernamedrew.synthetichumancorestarter.commands.CommandPriority;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class CommandProcessorImpl implements CommandProcessor {
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    public void processCommand(Command command) {
        if (command.getPriority() == CommandPriority.CRITICAL) {
            executeCommand(command);
        } else {
            executor.submit(() -> executeCommand(command));
        }
    }

    private void executeCommand(Command command) {
        log.info("Executing command {}", command);
    }
}
