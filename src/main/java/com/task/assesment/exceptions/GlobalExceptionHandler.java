package com.task.assesment.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<?> handleNotFoundException(NotFoundException notFoundException) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundException.getMessage());
  }

  @ExceptionHandler(MissingMandatoryFieldsException.class)
  public ResponseEntity<?> handleMandatoryFieldsException(
      MissingMandatoryFieldsException missingMandatoryFieldsException) {
    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(missingMandatoryFieldsException.getMessage());
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException unauthorizedException) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(unauthorizedException.getMessage());
  }
}
