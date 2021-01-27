package com.figure.store.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * author LucasDonizeti
 */

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class ConstraintViolationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ConstraintViolationException(String message) {
        super(message);
    }
}
