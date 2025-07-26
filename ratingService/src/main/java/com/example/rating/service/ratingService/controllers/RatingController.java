package com.example.rating.service.ratingService.controllers;

import com.example.rating.service.ratingService.entities.Rating;
import com.example.rating.service.ratingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Rating> createRatings(@RequestBody Rating rating)
    {
        String random_id = UUID.randomUUID().toString();
        rating.setRatingId(random_id);
        Rating rating1 = ratingService.createRatings(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<Rating> getAllRatingsById(@PathVariable("ratingId") String ratingId)
    {
        Rating rating = ratingService.getAllByRatingId(ratingId);
        return ResponseEntity.ok(rating);
    }

    @GetMapping()
    public ResponseEntity<List<Rating>> getAllRatings()
    {
        List<Rating> ratings = ratingService.getAllRatinga();
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("users/{userId}")
    public ResponseEntity<List<Rating>> getAllRatingsByuserId(@PathVariable("userId") String userId)
    {
        List<Rating> ratings = ratingService.getAllByUserId(userId);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getAllRatingsByhotelId(@PathVariable("hotelId") String hotelId)
    {
        List<Rating> ratings = ratingService.getAllByHotelId(hotelId);
        return ResponseEntity.ok(ratings);
    }

}
