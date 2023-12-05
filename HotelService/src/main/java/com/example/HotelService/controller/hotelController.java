package com.example.HotelService.controller;

import com.example.HotelService.entities.Hotel;
import com.example.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class hotelController {
    @Autowired
    private HotelService hotelService;
    //create
    @PostMapping
    ResponseEntity<Hotel> saveHotel(@RequestBody Hotel hotel){
        return new ResponseEntity<>(hotelService.create(hotel), HttpStatus.CREATED);

    }
    //get single
    @GetMapping("/{id}")
    ResponseEntity<Hotel> fetchHotel(@PathVariable("id") String hotelId){
        return new ResponseEntity<>(hotelService.get(hotelId),HttpStatus.OK);
    }
    // get aLl
    @GetMapping
    ResponseEntity<List<Hotel>> saveHotel(){
        return new ResponseEntity<>(hotelService.getAll(), HttpStatus.CREATED);
    }

}
