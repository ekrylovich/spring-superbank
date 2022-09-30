package com.superbank.technical.exception;

public class EntityNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 2549134942376428574L;

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(final String message) {
        super(message);
    }
}
