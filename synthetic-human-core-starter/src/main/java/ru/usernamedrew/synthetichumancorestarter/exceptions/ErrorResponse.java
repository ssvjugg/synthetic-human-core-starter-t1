package ru.usernamedrew.synthetichumancorestarter.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ErrorResponse {
    private final String message;
    private final String status;
}
