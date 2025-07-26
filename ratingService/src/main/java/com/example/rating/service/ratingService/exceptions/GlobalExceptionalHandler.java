package com.example.rating.service.ratingService.exceptions;

import com.example.rating.service.ratingService.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(RatingNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerRatingNotFoundException(RatingNotFoundException ratingNotFoundException)
    {
        String message = ratingNotFoundException.getMessage();
        ApiResponse response = ApiResponse.builder().message(message).success(false).httpStatus(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
