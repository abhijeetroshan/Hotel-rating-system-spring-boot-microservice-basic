package com.example.user.service.external.service;

import com.example.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("RATING-SERVICE")
public interface RatingService {

    @GetMapping("/ratings/users/{userId}")
    public List<Rating> getAllRatingsByuserId(@PathVariable("userId") String userId);

    @GetMapping("/ratings/hotels/{hotelId}")
    public List<Rating> getAllRatingsByhotelId(@PathVariable("hotelId") String hotelId);

    @GetMapping("/ratings/{ratingId}")
    public Rating getAllRatingsById(@PathVariable("ratingId") String ratingId);

    @GetMapping("/ratings")
    public List<Rating> getAllRatings();

    @PostMapping("/ratings")
    public Rating createRatings(@RequestBody Rating rating);

    @PutMapping("/ratings/{ratingId}")
    public ResponseEntity<Rating> updateAllRatingsById(@PathVariable("ratingId") String ratingId,Rating rating);

    @DeleteMapping("/ratings/{ratingId}")
    public void deleteRatingsWIthId(@PathVariable ("ratingId") String ratingId);


}
