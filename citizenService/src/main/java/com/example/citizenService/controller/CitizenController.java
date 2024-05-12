package com.example.citizenService.controller;

import com.example.citizenService.entity.Citizen;
import com.example.citizenService.repo.CitizenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/citizen")
public class CitizenController {
    @Autowired
    private CitizenRepo citizenRepo;

    @GetMapping("/{id}")
    public ResponseEntity<Citizen> getCitizenById(@PathVariable Integer id) {
        Optional<Citizen> citizenbyId = citizenRepo.findById(id);
        if(citizenbyId.isPresent()) {
            return new ResponseEntity<>(citizenbyId.get(), HttpStatus.OK);
        }else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<List<Citizen>> gevaccinationById(@PathVariable Integer id){
        List<Citizen> vaccinationbyId = citizenRepo.findByVaccinationCenterId(id);
        return new ResponseEntity<>(vaccinationbyId, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen citizen){
        Citizen saveCitizen = citizenRepo.save(citizen);
        return new ResponseEntity<>(saveCitizen,HttpStatus.CREATED);
    }

}
