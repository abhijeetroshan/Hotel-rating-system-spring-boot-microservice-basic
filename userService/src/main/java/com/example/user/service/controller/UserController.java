package com.example.user.service.controller;

import com.example.user.service.entities.User;
import com.example.user.service.services.IUserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public ResponseEntity<User> createUserEntity(@RequestBody User user)
    {
        User user1 = userService.saveUser(user);
        System.out.println("user1 = " + user1);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

//    To test the rate limiter we can the Jmeter Tool
    int retyCount = 1;
    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable("userId") String userId)
    {
        logger.info("Retry Count {}", retyCount);
        ++retyCount;
        User user = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }
    // Creating fallback method for circuit breaker
    // Parameter and method return type should be same as the caller method.
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception exception)
    {
        logger.info("Fallback method is called because either Rating service is down or Hotel service.");
        User user = User.builder().email("dummyMail@gmail.com").name("DUMMY").about("This is created as dummy because some service is DOWN").build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> userList = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }
}
