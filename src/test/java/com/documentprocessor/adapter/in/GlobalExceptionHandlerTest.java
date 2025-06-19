package com.documentprocessor.adapter.in;

import com.documentprocessor.core.exceptions.DomainException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;

import java.util.List;

import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {
    @Test
    void shouldHandleDomainException() {
        var handler = new GlobalExceptionHandler();
        var exception = mock(DomainException.class);
        when(exception.getMessage()).thenReturn("error");
        when(exception.getDetails()).thenReturn(List.of("detail1"));
        when(exception.getHttpStatus()).thenReturn(HttpStatus.BAD_REQUEST);

        var response = handler.handleDomainException(exception);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Assertions.assertThat(response.getBody().message()).isEqualTo("error");
        Assertions.assertThat(response.getBody().details()).containsExactly("detail1");
    }

    @Test
    void shouldHandleMethodArgumentNotValidException() {
        var handler = new GlobalExceptionHandler();
        var ex = mock(MethodArgumentNotValidException.class);
        var fieldError = new FieldError("object", "field", "must not be null");
        when(ex.getFieldErrors()).thenReturn(List.of(fieldError));

        var response = handler.handleMethodArgumentNotValidException(ex);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Assertions.assertThat(response.getBody().message()).isEqualTo("VALIDATION_EXCEPTION");
        Assertions.assertThat(response.getBody().details()).contains("field: must not be null");
    }
}
