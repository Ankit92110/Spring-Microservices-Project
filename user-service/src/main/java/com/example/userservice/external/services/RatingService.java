package com.example.userservice.external.services;

import com.example.userservice.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name ="RATING-SERVICE")
public interface RatingService {
    // GET
    @GetMapping("/ratings/users/{userId}")
    List<Rating>getRatings(@PathVariable String userId);

    // POST
    @PostMapping("ratings")
     public Rating createRating(Rating values);

    // PUT
  @PutMapping("/ratings/{ratingId}")
    public Rating updateRating(@PathVariable String ratingId,Rating rating);

  @DeleteMapping("/ratings/{ratingId}")
  public void deleteRatting(@PathVariable String ratingId);
}
