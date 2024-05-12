package com.codeDecode.foodCatalog.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeDecode.foodCatalog.dto.FoodCatalogPage;
import com.codeDecode.foodCatalog.dto.FoodItemDTO;
import com.codeDecode.foodCatalog.service.FoodCatalogService;

@RestController
@RequestMapping("/foodCatalog")
public class FoodCatalogController {
	
	@Autowired
	FoodCatalogService foodCatalogService;
	
	@PostMapping("/addFood")
	public ResponseEntity<FoodItemDTO> createFoodItem(@RequestBody FoodItemDTO foodItemDTO){
		FoodItemDTO savedFoodItem = foodCatalogService.createFoodItem(foodItemDTO);
		return new ResponseEntity<FoodItemDTO>(savedFoodItem, HttpStatus.CREATED);
	}
	@GetMapping("/fetchRestaurantAndFoodItemById/{restaurantId}")
	public ResponseEntity<FoodCatalogPage> fetchRestaurantDetailsWithFoodMenu(@PathVariable Integer restaurantId) {
		FoodCatalogPage foodCatalogPage = foodCatalogService.fetchFoodCatalogPageDetails(restaurantId);
		return new ResponseEntity<FoodCatalogPage>(foodCatalogPage,HttpStatus.OK);
	}
	@GetMapping("/allFoodItemsList")
	public ResponseEntity<List<FoodItemDTO>> fetchAllRestaurants(){
		List<FoodItemDTO> allFoodItems = foodCatalogService.findAllFoodItem();
		return new ResponseEntity<>(allFoodItems, HttpStatus.OK);
	}

}
