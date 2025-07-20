package ru.usernamedrew.synthetichumancorestarter.exceptions;

public class QueueOverflowException extends RuntimeException{
    public QueueOverflowException() {
        super("Queue overflow");
    }
}
