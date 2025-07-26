package com.example.rating.service.ratingService.repositories;

import com.example.rating.service.ratingService.entities.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,String> {

    public List<Rating> getAllByUserId(String userId);

    public List<Rating> getAllByHotelId(String hotelId);
}
