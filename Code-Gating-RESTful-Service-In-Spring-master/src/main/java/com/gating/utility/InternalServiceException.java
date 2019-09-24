package com.gating.utility;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServiceException extends RuntimeException{

  private static final long serialVersionUID = 1L;

  public InternalServiceException(String message, Throwable e) {
    super(message, e);
  }

}
