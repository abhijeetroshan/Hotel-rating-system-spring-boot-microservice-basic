package com.example.hotel.service.services;

import com.example.hotel.service.entities.Hotel;
import com.example.hotel.service.exceptions.HotelNotFoundException;
import com.example.hotel.service.repositories.HotelRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelService implements IHotelService {

    @Autowired
    private HotelRepository hotelRepository;


    @Override
    public Hotel addHotel(Hotel hotel) {

        String uid = UUID.randomUUID().toString();
        hotel.setId(uid);
        System.out.println("hotel = " + hotel.getName());
        Hotel hotel1 = hotelRepository.save(hotel);
        return hotel1;
    }

    @Override
    public List<Hotel> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels;
    }

    @Override
    public Hotel getHotel(String hotelID) {
        Hotel hotel = hotelRepository.findById(hotelID).orElseThrow(() ->
                new HotelNotFoundException("The hotel with id" + hotelID + " which you are searching for is not found in the DB"));
        return hotel;
    }
}
