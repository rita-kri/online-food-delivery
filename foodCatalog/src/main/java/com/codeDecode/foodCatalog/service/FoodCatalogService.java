package com.codeDecode.foodCatalog.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.codeDecode.foodCatalog.dto.FoodCatalogPage;
import com.codeDecode.foodCatalog.dto.FoodItemDTO;
import com.codeDecode.foodCatalog.dto.Resturant;
import com.codeDecode.foodCatalog.entity.FoodItem;
import com.codeDecode.foodCatalog.repo.FoodItemRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FoodCatalogService {
	
	@Autowired
	FoodItemRepo foodItemRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public FoodItem dtoToEntity(FoodItemDTO foodItemDTO) {
		FoodItem mapToEntity = this.modelMapper.map(foodItemDTO, FoodItem.class);
		return mapToEntity;
	}
	
	public FoodItemDTO entityToDto(FoodItem foodItem) {
		FoodItemDTO mapToDto = this.modelMapper.map(foodItem, FoodItemDTO.class);
		return mapToDto;
	}

	public FoodItemDTO createFoodItem(FoodItemDTO foodItemDTO) {
		FoodItem dtoToEntity = this.dtoToEntity(foodItemDTO);
		FoodItem savedFood = foodItemRepo.save(dtoToEntity);
		return this.entityToDto(savedFood);
	}

	public FoodCatalogPage fetchFoodCatalogPageDetails(Integer resturantId)  {
		List<FoodItem> foodItemList = fetchFoodItemList(resturantId);  //food item list
		Resturant resturant = fetchResturantDetailsFromResturantMS(resturantId);  //resturant details
		return createFoodCatalogPage(foodItemList, resturant);
		
	}

	private FoodCatalogPage createFoodCatalogPage(List<FoodItem> foodItemList, Resturant resturant) {
		FoodCatalogPage foodCatalogPage =new FoodCatalogPage();
		foodCatalogPage.setFoodItemList(foodItemList);
		foodCatalogPage.setResturant(resturant);
		return foodCatalogPage;
	}

	private Resturant fetchResturantDetailsFromResturantMS(Integer resturantId)  {
		//Resturant resturant = restTemplate.getForObject("http://RESTAURANT-SERVICE/fetchById/"+resturantId, Resturant.class);
		// Replace underscore with hyphen in the hostname
        String serviceUrl = "http://RESTAURANT-SERVICE/restaurant/fetchById/"+resturantId;
     // Use the corrected URL to make the request
        Resturant resturant = restTemplate.getForObject(serviceUrl, Resturant.class);
        return resturant;
       
	}

	private List<FoodItem> fetchFoodItemList(Integer resturantId) {
		return foodItemRepo.findByResturantId(resturantId);
	}


}
