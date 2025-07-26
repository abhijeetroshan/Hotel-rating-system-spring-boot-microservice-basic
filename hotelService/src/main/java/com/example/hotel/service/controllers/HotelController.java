package com.example.hotel.service.controllers;

import com.example.hotel.service.entities.Hotel;
import com.example.hotel.service.services.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("hotels")
public class HotelController {

    @Autowired
    private IHotelService hotelService;

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
    {
        System.out.println("hotel.getName() = " + hotel.getName());
        Hotel hotel1 = hotelService.addHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable("hotelId") String hotelId)
    {
        Hotel hotel = hotelService.getHotel(hotelId);
        System.out.println(hotel);
        return ResponseEntity.status(HttpStatus.OK).body(hotel);
    }

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels()
    {
        List<Hotel> hotelList = hotelService.getAllHotels();
        return ResponseEntity.status(HttpStatus.OK).body(hotelList);
    }
}
