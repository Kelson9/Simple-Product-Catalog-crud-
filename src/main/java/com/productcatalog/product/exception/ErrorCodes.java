package com.productcatalog.product.exception;

public enum ErrorCodes {
    USER_NOT_FOUND("User Not found"),
    RESOURCE_NOT_FOUND("Resource not found"),
    ACCESS_DENIED("Access is denied"),
    ACCESS_FORBIDDEN("Access is forbidden"),
    VALIDATION_ERROR("Validation Error"),
    INT_SERVER_ERROR("Internal Server error"),
    BAD_CREDENTIALS("Bad Credentials");

    private String message;

    ErrorCodes(String message) {
      this.message = message;
    }

    public String getMessage() {
      return message;
    }
  }

