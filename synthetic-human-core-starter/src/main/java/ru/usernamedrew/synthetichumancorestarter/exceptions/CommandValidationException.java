package ru.usernamedrew.synthetichumancorestarter.exceptions;

public class CommandValidationException extends RuntimeException {
    public CommandValidationException(String message) {
        super(message);
    }
}
