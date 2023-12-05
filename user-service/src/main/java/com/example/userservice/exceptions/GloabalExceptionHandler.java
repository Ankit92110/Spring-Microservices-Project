package com.example.userservice.exceptions;

import com.example.userservice.payload.ApiResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GloabalExceptionHandler {
   @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponce> handlerResourceNotFoundException(ResourceNotFoundException exception){
        String message= exception.getMessage();
      ApiResponce build=  ApiResponce.builder().message(message).status(HttpStatus.NOT_FOUND).build();
      return new ResponseEntity<>(build,HttpStatus.NOT_FOUND);
    }
}
