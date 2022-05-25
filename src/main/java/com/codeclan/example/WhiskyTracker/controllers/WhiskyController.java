package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
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
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;
    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping("/whiskies")
    public ResponseEntity<List<Whisky>> mainRoute(@RequestParam(name="age", required = false)String age,
                                                  @RequestParam(name="distillery", required= false) String distillery,
                                                  @RequestParam(name= "year", required=false)String year,
                                                  @RequestParam(name="region", required=false)String region){
        if(year != null){
            int yearInt = valueOf(year);
            return new ResponseEntity<>(whiskyRepository.findByYear(yearInt), HttpStatus.OK);
        }
        else if (region != null){
            return new ResponseEntity<>(whiskyRepository.findByDistilleryRegion(region), HttpStatus.OK);
        }
        else if(age != null && distillery != null){
            int ageInt = valueOf(age);
            Distillery distilleryObj = distilleryRepository.findByName(distillery);
            return new ResponseEntity<>(whiskyRepository.findByDistilleryAndAge(distilleryObj, ageInt), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

}
