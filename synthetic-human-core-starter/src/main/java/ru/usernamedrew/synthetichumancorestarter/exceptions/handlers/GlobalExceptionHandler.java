package ru.usernamedrew.synthetichumancorestarter.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.usernamedrew.synthetichumancorestarter.exceptions.CommandValidationException;
import ru.usernamedrew.synthetichumancorestarter.exceptions.ErrorResponse;

import java.util.concurrent.RejectedExecutionException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, Throwable cause) {
        ErrorResponse errorResponse = new ErrorResponse(status.getReasonPhrase(), cause.getMessage());

        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(CommandValidationException.class)
    public ResponseEntity<ErrorResponse> handleCommandValidationException(CommandValidationException e) {
        return buildResponse(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(RejectedExecutionException.class)
    public ResponseEntity<ErrorResponse> handleRejectedExecutionException(RejectedExecutionException e) {
        return buildResponse(HttpStatus.SERVICE_UNAVAILABLE, e);
    }
}
