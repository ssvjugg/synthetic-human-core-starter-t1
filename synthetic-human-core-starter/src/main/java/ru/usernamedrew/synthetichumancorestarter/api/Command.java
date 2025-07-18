package ru.usernamedrew.synthetichumancorestarter.api;

import ru.usernamedrew.synthetichumancorestarter.commands.CommandPriority;

public interface Command {
    String getDescription();
    CommandPriority getPriority();
    String getAuthor();
}
