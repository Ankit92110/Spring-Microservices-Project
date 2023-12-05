package com.example.userservice.services;

import com.example.userservice.entities.Hotel;
import com.example.userservice.entities.Rating;
import com.example.userservice.entities.User;
import com.example.userservice.exceptions.ResourceNotFoundException;
import com.example.userservice.external.services.HotelService;
import com.example.userservice.external.services.RatingService;
import com.example.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private RatingService ratingService;
    @Override
    public User saveUser(User user) {
        String randomUserid=UUID.randomUUID().toString();
        user.setUserId(randomUserid);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {

        return userRepository.findAll();
        // implementing rating service call for each user as well future scope usnng restTemplate
    }

    @Override
    public User getUser(String userId) {
        // Get uer from data dataBase with the help of user Repository
                User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on server !!"+ userId));
                //Fetch rating of the above user from Rating Service
        //http://localhost:8083/ratings/users/6fd41594-dc46-41f9-a21b-1a4ab3579cef
//        String url="http://RATING-SERVICE/ratings/users/"+user.getUserId();
//        Rating[] ratingsOfUser= restTemplate.getForObject(url, Rating[].class);
//        assert ratingsOfUser != null;
//        List<Rating> ratings=Arrays.stream(ratingsOfUser).toList();
        List<Rating> ratings=ratingService.getRatings(user.getUserId());
        List<Rating>res=new ArrayList<>();
        for(Rating r:ratings){
//            String uri="http://HOTEL-SERVICE/hotels/"+r.getHotelId();
//            System.out.println(uri);
       //Hotel hotel=  restTemplate.getForObject(uri, Hotel.class);

            Hotel hotel=hotelService.getHotel(r.getHotelId());
            System.out.println(hotel);
             //set the hotel to rating
           r.setHotel(hotel);
           res.add(r);
        }
      user.setRatings(res);
                return user;
    }

}
