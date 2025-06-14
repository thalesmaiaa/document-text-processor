package com.documentprocessor.core.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public abstract class DomainException extends RuntimeException {

    private final List<String> details;
    private final HttpStatus httpStatus;

    protected DomainException(String message, List<String> details, HttpStatus httpStatus) {
        super(message);
        this.details = details;
        this.httpStatus = httpStatus;
    }
}
