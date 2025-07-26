package com.example.user.service.services;

import com.example.user.service.entities.Hotel;
import com.example.user.service.entities.Rating;
import com.example.user.service.entities.User;
import com.example.user.service.exceptions.ResourceNotFoundException;
import com.example.user.service.external.service.HotelService;
import com.example.user.service.external.service.RatingService;
import com.example.user.service.repositories.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService{

    @Autowired
    public IUserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public User saveUser(User user) {
        String uid = UUID.randomUUID().toString();
        user.setUserId(uid);
        User user1 = userRepository.save(user);
        return user1;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
//        userList.stream().map(x->x.getRatings())
        return userList;
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("The " + userId + " Which you are searching is not found in the DB"));
        System.out.println("user = " + user);

//        Rating[] arrayRating = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + userId, Rating[].class);
//        System.out.println("ratings = " + Arrays.toString(arrayRating));

//        List<Rating> ratings = Arrays.stream(arrayRating).toList();
        List<Rating> ratings = ratingService.getAllRatingsByuserId(userId);
        List<Rating> updatedRatings = ratings.stream().map(rating -> {
            // API call to hotels to get the hotels
            // Set the hotels to rating
            // return the rating
//            ResponseEntity<Hotel> hotels = restTemplate.getForEntity("http://localhost:8082/hotels/" + rating.getHotelId(), Hotel.class);
//            rating.setHotel(hotels.getBody());
            Hotel hotel = hotelService.getHotelById(rating.getHotelId());
            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRatings(updatedRatings);
        return user;
    }
}
