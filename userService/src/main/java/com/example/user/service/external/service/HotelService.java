package com.example.user.service.external.service;

import com.example.user.service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/hotels/{hotelId}")
    public Hotel getHotelById(@PathVariable("hotelId") String hotelId);

    @PostMapping("/hotels")
    public Hotel createHotel(@RequestBody Hotel hotel);

    @GetMapping("/hotels")
    public List<Hotel> getAllHotels();

}
