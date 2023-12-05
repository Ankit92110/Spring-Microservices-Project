package com.example.RatingService.service;

import com.example.RatingService.entities.Rating;

import java.util.List;

public interface RatingService {
    //create
   Rating create(Rating rating);
    // get all rating
List<Rating> getRatings();

    // get all by user id
List<Rating> getRatingByUserId(String id);
    // get all by hotel id
    List<Rating> getRatingByHotelId(String hotelId);
}
