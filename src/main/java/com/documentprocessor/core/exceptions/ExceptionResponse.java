package com.documentprocessor.core.exceptions;

import java.util.List;

public record ExceptionResponse(String message, List<String>details) {
}
