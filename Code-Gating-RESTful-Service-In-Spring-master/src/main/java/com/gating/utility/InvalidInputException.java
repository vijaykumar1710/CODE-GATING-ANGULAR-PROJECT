package com.gating.utility;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputException extends RuntimeException{

  private static final long serialVersionUID = 1L;

  public InvalidInputException(String message, String userInput) {
    super(message + ":" + userInput);
  }

}
