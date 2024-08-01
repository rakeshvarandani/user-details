package com.practise.user_details.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.practise.user_details.model.Car;
import com.practise.user_details.model.User;
import com.practise.user_details.repository.CarRepo;
import com.practise.user_details.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;



@Slf4j
@RestController
@RequestMapping("/user-details")
public class UserController {


    Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    UserRepository userRepository;

    @Autowired
    CarRepo carRepo;



    @GetMapping("/getUser/{id}")
    public Mono<Optional<User>> getByID(@PathVariable String id){
        return Mono.just(userRepository.findById(id));
    }

    @GetMapping("/getAllUsers")
    public Flux<List<User>> getAllUsers(){
        return Flux.just(userRepository.findAll());
    }

    @GetMapping("/util")
    public void method2() throws JsonProcessingException {
        logger.info("returning 1", "no error happened");

        User user1 = new User();
        user1.setId("1");
        user1.setName("Rakesh");

        Car balenoCar =  new Car();
        balenoCar.setCarCompany("Maruti");
        balenoCar.setUser(user1);
        balenoCar.setCarCompany("A");
        balenoCar.setPurchaseDate(Date.valueOf(LocalDate.of(2013,9,3)));

        List<Car> rakeshcar =  new ArrayList<>();
        rakeshcar.add(balenoCar);
        user1.setCarowned(rakeshcar);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(user1);
        System.out.println(json);

    }

    @PostMapping("/addUser")
    public Mono<User> addUser(@RequestBody User user){
        return Mono.just(userRepository.save(user));
    }

    @PostMapping("/addCar")
    public Mono<Car> addCar(@RequestBody Car car){
        return Mono.just(carRepo.save(car));
    }

}
