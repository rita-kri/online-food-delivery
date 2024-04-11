package com.fullstackapplication.restaurantListing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fullstackapplication.restaurantListing.DTO.ResturantDTO;
import com.fullstackapplication.restaurantListing.service.ResturantService;

@RestController
@RequestMapping("/restaurant")
public class ResturantController {
	
	@Autowired
	ResturantService resturantService;
	
	@GetMapping("/allResturants")
	public ResponseEntity<List<ResturantDTO>> fetchAllResturants(){
		List<ResturantDTO> allResturants = resturantService.findAllResturants();
		return new ResponseEntity<>(allResturants, HttpStatus.OK);
	}
	
	@PostMapping("/addResturant")
	public ResponseEntity<ResturantDTO> createResturant(@RequestBody ResturantDTO resturantDTO){
		ResturantDTO resturantAdded= resturantService.createResturant(resturantDTO);
		return new ResponseEntity<ResturantDTO>(resturantAdded, HttpStatus.CREATED);
	}
	
	@GetMapping("/fetchById/{id}")
		public ResponseEntity<ResturantDTO> findResturantById(@PathVariable Integer id){
			return resturantService.getResturantById(id);
		}
	}


