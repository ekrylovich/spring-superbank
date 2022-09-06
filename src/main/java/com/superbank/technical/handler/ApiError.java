package com.superbank.technical.handler;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiError {
    public final HttpStatus status;
    public final ZonedDateTime dateTime;
    public final String message;
    public final String debugMessage;

    public ApiError(HttpStatus status, ZonedDateTime dateTime, String message, String debugMessage) {
        this.status = status;
        this.dateTime = dateTime;
        this.message = message;
        this.debugMessage = debugMessage;
    }
}
