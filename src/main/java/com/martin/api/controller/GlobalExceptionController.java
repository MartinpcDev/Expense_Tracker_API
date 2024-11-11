package com.martin.api.controller;

import com.martin.api.exception.ExpenseNotFoundException;
import com.martin.api.exception.InvalidAuthException;
import com.martin.api.exception.NotPermitedActionException;
import com.martin.api.exception.UserNotFoundException;
import com.martin.api.util.dto.ExceptionResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

  @ExceptionHandler(InvalidAuthException.class)
  public ResponseEntity<ExceptionResponse> handleNoAuth(InvalidAuthException e) {
    ExceptionResponse response = new ExceptionResponse(
        "Error de autenticacion",
        e.getMessage(),
        null
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(response);
  }

  @ExceptionHandler(ExpenseNotFoundException.class)
  public ResponseEntity<ExceptionResponse> handleAllTaskNotFound(ExpenseNotFoundException e) {
    ExceptionResponse response = new ExceptionResponse(
        "No se encontro el Recurso",
        e.getMessage(),
        null
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(response);
  }

  @ExceptionHandler(NotPermitedActionException.class)
  public ResponseEntity<ExceptionResponse> handleAllTaskPermissions(NotPermitedActionException e) {
    ExceptionResponse response = new ExceptionResponse(
        "Accion no permitida",
        e.getMessage(),
        null
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(response);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ExceptionResponse> handleAllUserNotFound(UserNotFoundException e) {
    ExceptionResponse response = new ExceptionResponse(
        "No se encontro el Recurso",
        e.getMessage(),
        null
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(response);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionResponse> handleArgumentsInvalid(
      MethodArgumentNotValidException e) {
    List<ObjectError> errors = e.getAllErrors();
    List<String> details = errors.stream()
        .map(error -> {
          if (error instanceof FieldError fieldError) {
            return fieldError.getField() + " : " + fieldError.getDefaultMessage();
          }
          return error.getDefaultMessage();
        }).toList();

    ExceptionResponse response = new ExceptionResponse(
        "Error en los datos ingresados",
        e.getMessage(),
        details
    );

    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponse> handleAllException(Exception e) {
    ExceptionResponse response = new ExceptionResponse(
        "Ocurrio un error inesperado",
        e.getMessage(),
        null
    );
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(response);
  }
}
