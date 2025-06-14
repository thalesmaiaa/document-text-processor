package com.documentprocessor.core.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public abstract class DomainException extends RuntimeException {

    protected String message;
    protected List<String> details;
    protected HttpStatus httpStatus;
}
