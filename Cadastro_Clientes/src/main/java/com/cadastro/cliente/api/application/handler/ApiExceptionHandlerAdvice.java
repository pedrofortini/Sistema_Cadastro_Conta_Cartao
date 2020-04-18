package com.cadastro.cliente.api.application.handler;

import com.cadastro.cliente.api.application.exception.ResourceAlreadyExistsException;
import com.cadastro.cliente.api.application.exception.ResourceNotFoundException;
import com.cadastro.cliente.api.application.exception.UnprocessableEnityException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.cadastro.cliente.api.application.exception.PersistenceException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@ControllerAdvice
@Order(HIGHEST_PRECEDENCE)
public class ApiExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleExcecaoGenerica(Exception exception) {

        Gson gsonBuilder = new GsonBuilder().create();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(gsonBuilder.toJson(exception.getMessage()));
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<?> handlePersistenceException(PersistenceException exception) {

        Gson gsonBuilder = new GsonBuilder().create();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(gsonBuilder.toJson(exception.getMessage()));
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<?> handleResourceAlreadyExistsException(ResourceAlreadyExistsException exception) {

        Gson gsonBuilder = new GsonBuilder().create();

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(gsonBuilder.toJson(exception.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {

        Gson gsonBuilder = new GsonBuilder().create();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(gsonBuilder.toJson(exception.getMessage()));
    }

    @ExceptionHandler(UnprocessableEnityException.class)
    public ResponseEntity<?> handleUnprocessableEnityException(UnprocessableEnityException exception) {

        Gson gsonBuilder = new GsonBuilder().create();

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(gsonBuilder.toJson(exception.getMessage()));
    }
}
