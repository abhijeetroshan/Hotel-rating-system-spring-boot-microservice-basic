package com.example.rating.service.ratingService.services.Impl;

import com.example.rating.service.ratingService.entities.Rating;
import com.example.rating.service.ratingService.exceptions.RatingNotFoundException;
import com.example.rating.service.ratingService.repositories.RatingRepository;
import com.example.rating.service.ratingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;


    @Override
    public Rating createRatings(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatinga() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getAllByUserId(String userId) {
        return ratingRepository.getAllByUserId(userId);
    }

    @Override
    public List<Rating> getAllByHotelId(String hotelId) {
        return ratingRepository.getAllByHotelId(hotelId);
    }

    @Override
    public Rating getAllByRatingId(String ratingId) {
        return ratingRepository.findById(ratingId).orElseThrow(()-> new RatingNotFoundException("No rating found for " + ratingId + " Rating id"));
    }
}
