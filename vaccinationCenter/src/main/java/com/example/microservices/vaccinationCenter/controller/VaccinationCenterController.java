package com.example.microservices.vaccinationCenter.controller;

import com.example.microservices.vaccinationCenter.entity.VaccinationCenter;
import com.example.microservices.vaccinationCenter.modal.Citizen;
import com.example.microservices.vaccinationCenter.modal.RequiredResponse;
import com.example.microservices.vaccinationCenter.repo.VaccinationCenterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/vaccinationcenter")
public class VaccinationCenterController {

    @Autowired
    private VaccinationCenterRepo centerRepo;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/add")
    public ResponseEntity<VaccinationCenter> addCenter(@RequestBody VaccinationCenter center){
        VaccinationCenter saveCentered = centerRepo.save(center);
        return new ResponseEntity<>(saveCentered, HttpStatus.CREATED);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<RequiredResponse> getAllDataBasedOnCenterId(@PathVariable Integer id) {
        RequiredResponse requiredResponse = new RequiredResponse();

        // Get vaccination center details
        VaccinationCenter center = centerRepo.findById(id).orElse(null);
        if (center == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        requiredResponse.setCenter(center);

        // Get all citizens registered to the vaccination center
        ResponseEntity<Citizen[]> responseEntity = restTemplate.getForEntity(
                "http://CITIZENSHIP-SERVICE/citizen/id/" + id,
                Citizen[].class
        );
        Citizen[] citizensArray = responseEntity.getBody();
        if (citizensArray != null) {
            List<Citizen> listOfCitizens = Arrays.asList(citizensArray);
            requiredResponse.setCitizens(listOfCitizens);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(requiredResponse, HttpStatus.OK);
    }
}
//    public ResponseEntity<RequiredResponse> getAllDataBasedOnCenterId(@PathVariable Integer id){
//        RequiredResponse requiredResponse= new RequiredResponse();
//        //get all vaccination center details
//        VaccinationCenter center = centerRepo.findById(id).get();
//        requiredResponse.setCenter(center);
//        //get all citizens registered to vaccination center
//        Citizen listOfCitizens = restTemplate.getForObject("http://localhost:8081/citizen/id/" + id, Citizen.class);
//        requiredResponse.setCitizens((List<Citizen>) listOfCitizens);
//        return  new ResponseEntity<RequiredResponse>(requiredResponse, HttpStatus.OK);
//
//    }

