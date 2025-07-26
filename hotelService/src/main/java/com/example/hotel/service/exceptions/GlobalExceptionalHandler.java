package com.example.hotel.service.exceptions;

import com.example.hotel.service.payload.ApiRssponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(HotelNotFoundException.class)
    ResponseEntity<ApiRssponse> handlerHotelNotFoundException(HotelNotFoundException hotelNotFoundException)
    {
        ApiRssponse apiRssponse = ApiRssponse.builder()
                .message(hotelNotFoundException.getMessage()).success(false)
                .httpStatus(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(apiRssponse,HttpStatus.NOT_FOUND);
    }
}
