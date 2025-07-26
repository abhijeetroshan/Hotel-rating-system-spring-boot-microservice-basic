package com.example.rating.service.ratingService.exceptions;

public class RatingNotFoundException extends RuntimeException {
    public RatingNotFoundException(String message) {
        super(message);
    }

    public RatingNotFoundException()
    {
        super("Rating Not found");
    }
}
