package com.example.userservice.controller;

import com.example.userservice.entities.User;
import com.example.userservice.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    // create
     @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
         return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
     }
    //single user get
    int retryCount=1;
    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod = "ratingHotelFallBack")
    @Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallBack")
    public ResponseEntity<User> getSingleUser(@PathVariable("userId") String id) {
        log.info("Get Single user handler:UseController");
        log.info("Retry Count: {}",retryCount);
        retryCount++;
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    //creating fall back mehtod for circuitBreaker
    public ResponseEntity<User> ratingHotelFallBack(String userId, Exception e) {
        log.info("FallBack is executed because service is down", e.getMessage());
        User user = User.builder()
                .email("dummy@gmail.com")
                .name("Dummy")
                .userId("12312345678")
                .about("This user is created dummy because some services are down")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }



    // get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllusers(){
         return new ResponseEntity<>(userService.getAllUser(),HttpStatus.OK);
    }

}
