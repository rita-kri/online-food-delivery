package com.fullstackapplication.restaurantListing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fullstackapplication.restaurantListing.DTO.RestaurantDTO;
import com.fullstackapplication.restaurantListing.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin
public class RestaurantController {
	
	@Autowired
	RestaurantService restaurantService;
	
	@GetMapping("/allRestaurants")
	public ResponseEntity<List<RestaurantDTO>> fetchAllRestaurants(){
		List<RestaurantDTO> allRestaurants = restaurantService.findAllRestaurants();
		return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
	}
	
	@PostMapping("/addRestaurant")
	public ResponseEntity<RestaurantDTO> createRestaurant(@RequestBody RestaurantDTO restaurantDTO){
		RestaurantDTO restaurantAdded= restaurantService.createRestaurant(restaurantDTO);
		return new ResponseEntity<RestaurantDTO>(restaurantAdded, HttpStatus.CREATED);
	}
	
	@GetMapping("/fetchById/{id}")
		public ResponseEntity<RestaurantDTO> findRestaurantById(@PathVariable Integer id){
			return restaurantService.getRestaurantById(id);
		}
	}


