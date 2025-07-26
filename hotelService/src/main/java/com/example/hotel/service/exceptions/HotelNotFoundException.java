package com.example.hotel.service.exceptions;

public class HotelNotFoundException extends RuntimeException{

    public HotelNotFoundException(String s) {
        super(s);
    }
}
