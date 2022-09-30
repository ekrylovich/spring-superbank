package com.superbank.technical.handler;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiError {
    public final HttpStatus status;
    public final ZonedDateTime dateTime;
    public final String message;
    public final String debugMessage;

    public ApiError(final HttpStatus status,
                    final ZonedDateTime dateTime,
                    final String message,
                    final String debugMessage) {
        this.status = status;
        this.dateTime = dateTime;
        this.message = message;
        this.debugMessage = debugMessage;
    }
}
