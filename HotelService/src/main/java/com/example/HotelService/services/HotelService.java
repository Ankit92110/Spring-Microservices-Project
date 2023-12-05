package com.example.HotelService.services;

import com.example.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {
    //create
   Hotel create(Hotel hotel);
    //get all
   List<Hotel>getAll();
    //get
    Hotel get(String id);
}
