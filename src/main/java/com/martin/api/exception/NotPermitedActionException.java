package com.martin.api.exception;

public class NotPermitedActionException extends RuntimeException {

  public NotPermitedActionException(String message) {
    super(message);
  }
}
