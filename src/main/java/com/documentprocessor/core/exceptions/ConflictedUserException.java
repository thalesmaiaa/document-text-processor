package com.documentprocessor.core.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ConflictedUserException extends DomainException {

    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }

    public ConflictedUserException(String message) {
        this.message = "User with this %s already exists".formatted(message);
    }
}