package org.mateuszlaski.logon.common.controller;

import jakarta.servlet.Servlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.mateuszlaski.logon.common.exception.EmailAlreadyInUseException;
import org.mateuszlaski.logon.common.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Servlet servlet;

    public GlobalExceptionHandler(Servlet servlet) {
        this.servlet = servlet;
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<ExceptionResponse> handle(EmailAlreadyInUseException ex, HttpServletRequest servletRequest) {
        final HttpStatus httpStatus = HttpStatus.CONFLICT;

        var er = new ExceptionResponse(
            LocalDateTime.now(),
            httpStatus.value(),
            ex.getMessage(),
            servletRequest.getRequestURI()
        );

        return ResponseEntity
                .status(httpStatus)
                .body(er);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handle(MethodArgumentNotValidException ex, HttpServletRequest servletRequest) {
        final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(FieldError::getDefaultMessage)
            .toList();

        var er = new ExceptionResponse(
            LocalDateTime.now(),
            httpStatus.value(),
            errors,
            servletRequest.getRequestURI()
        );

        return ResponseEntity.status(httpStatus).body(er);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handle(BadCredentialsException ex, HttpServletRequest servletRequest) {
        final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        var er = new ExceptionResponse(
                LocalDateTime.now(),
                httpStatus.value(),
                ex.getMessage(),
                servletRequest.getRequestURI()
        );

        return ResponseEntity.status(httpStatus).body(er);
    }
}
