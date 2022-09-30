package com.superbank.technical.handler;

import com.superbank.technical.util.ErrorMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class ExceptionHandlers {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlers.class);

    private static final String DEFAULT_ERROR_MESSAGE = "contact.administrator";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(final Exception exp) {
        final String message = ErrorMessageUtil.get("contact.administrator");
        return buildErrorInfo(exp, message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntimeException(final RuntimeException exp) {
        return buildErrorInfo(exp, DEFAULT_ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exp) {
        final String errorMsg = exp.getBindingResult().getAllErrors().stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse(exp.getMessage());
        return buildErrorInfo(exp, errorMsg, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ApiError> buildErrorInfo(final Exception exp, final String message, final HttpStatus status) {
        LOGGER.error(exp.getMessage(), exp.hashCode() + ": " + exp);
        final ApiError apiError = new ApiError(status, ZonedDateTime.now(), message, String.valueOf(exp.hashCode()));
        return new ResponseEntity<>(apiError, null, status);
    }

}
