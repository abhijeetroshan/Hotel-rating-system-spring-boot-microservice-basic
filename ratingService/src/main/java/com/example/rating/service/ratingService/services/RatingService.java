package com.example.rating.service.ratingService.services;

import com.example.rating.service.ratingService.entities.Rating;

import java.util.List;

public interface RatingService {

    public Rating createRatings(Rating rating);

    public List<Rating> getAllRatinga();

    public List<Rating> getAllByUserId(String userId);

    public List<Rating> getAllByHotelId(String hotelId);

    public Rating getAllByRatingId(String ratingId);
}
