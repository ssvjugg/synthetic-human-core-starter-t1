package ru.usernamedrew.synthetichumancorestarter.exceptions;

public class QueueOverflowException extends RuntimeException{
    public QueueOverflowException(String message) {
        super(message);
    }
}
