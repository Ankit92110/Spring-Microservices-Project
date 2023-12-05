package com.example.HotelService.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;
@RestControllerAdvice
public class GlobalExceptionhandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<Map<String,Object>> notFoundHandler(ResourceNotFoundException exception){
        Map map=new HashMap();
        map.put("message",exception.getMessage());
        map.put("success",false);
        map.put("status", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
    }
}
