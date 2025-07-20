package ru.usernamedrew.synthetichumancorestarter.api;

import ru.usernamedrew.synthetichumancorestarter.commands.CommandPriority;

/**
 * Интерфейс команды
 */
public interface Command {
    String getDescription();
    CommandPriority getPriority();
    String getAuthor();
}
