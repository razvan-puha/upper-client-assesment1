package com.task.assesment.exceptions;

public class MissingMandatoryFieldsException extends RuntimeException {

  public MissingMandatoryFieldsException(String message) {
    super(message);
  }
}
