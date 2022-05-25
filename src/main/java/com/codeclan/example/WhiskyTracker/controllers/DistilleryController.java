package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.lang.Integer.valueOf;

@RestController
public class DistilleryController {

    @Autowired
    DistilleryRepository distilleryRepository;


    @GetMapping("/distilleries")
    public ResponseEntity<List<Distillery>> distilleryGetRoute(@RequestParam(name= "whiskyAge", required=false) String age,
                                                               @RequestParam(name="region", required=false)String region){
        if (age != null){
            int intage = valueOf(age);
            return new ResponseEntity<>(distilleryRepository.findByWhiskiesAge(intage), HttpStatus.OK );
        }
        else if(region != null){
            return new ResponseEntity<>(distilleryRepository.findByRegion(region), HttpStatus.OK);
        }
        return new ResponseEntity<>(distilleryRepository.findAll(), HttpStatus.OK);
    }
}
