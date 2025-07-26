package com.example.user.service.exceptions;


import com.example.user.service.entities.User;
import com.example.user.service.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException resourceNotFoundException)
    {
        System.out.println("Called from Global Exception");
        String m = resourceNotFoundException.getMessage();
        ApiResponse response = ApiResponse.builder().message(m).success(false).httpStatus(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//        return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
    }
}
