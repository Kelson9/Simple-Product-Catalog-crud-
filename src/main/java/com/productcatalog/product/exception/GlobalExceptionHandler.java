package com.productcatalog.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = {ResourceNotFoundException.class})
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request){
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode(ErrorCodes.RESOURCE_NOT_FOUND.toString());
    errorResponse.setMessage(ex.getMessage());
    errorResponse.setEndpoint(request.getRequestURI());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }


}
