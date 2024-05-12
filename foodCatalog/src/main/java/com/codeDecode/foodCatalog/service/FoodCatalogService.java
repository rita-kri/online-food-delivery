package com.codeDecode.foodCatalog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.codeDecode.foodCatalog.dto.FoodCatalogPage;
import com.codeDecode.foodCatalog.dto.FoodItemDTO;
import com.codeDecode.foodCatalog.dto.Restaurant;
import com.codeDecode.foodCatalog.entity.FoodItem;
import com.codeDecode.foodCatalog.repo.FoodItemRepo;


@Service
public class FoodCatalogService {
	
	@Autowired
	FoodItemRepo foodItemRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	RestTemplate restTemplate;

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
//	public FoodCatalogPage fetchFoodCatalogPageDetails(Integer resturantId) {
//		 // Fetch restaurant details
//        Resturant resturant = restaurantClient.getRestaurantById(resturantId);
//
//        // Fetch food items associated with the restaurant
//        List<FoodItem> foodItems = foodItemClient.getFoodItemsByRestaurantId(resturantId);
//
//        // Create a FoodCatalogPage object containing both restaurant and food items
//        FoodCatalogPage foodCatalogPage = new FoodCatalogPage();
//        foodCatalogPage.setResturant(resturant);
//        foodCatalogPage.setFoodItemList(foodItems);
//        return foodCatalogPage;
//	}
	

	public FoodCatalogPage fetchFoodCatalogPageDetails(Integer restaurantId)  {
		List<FoodItem> foodItemList = fetchFoodItemList(restaurantId);  //food item list
		Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMS(restaurantId);  //restaurant details
		return createFoodCatalogPage(foodItemList, restaurant);

	}

	private FoodCatalogPage createFoodCatalogPage(List<FoodItem> foodItemList, Restaurant restaurant) {
		FoodCatalogPage foodCatalogPage = new FoodCatalogPage();
		foodCatalogPage.setFoodItemList(foodItemList);
		foodCatalogPage.setRestaurant(restaurant);
		return foodCatalogPage;
	}

	//private Restaurant fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId)  {
//		String serviceUrl = catalogServiceEurekaConfig.getServiceUrl();
//		// Use the serviceUrl to construct the URL for the other service
//		String otherServiceUrl = serviceUrl + "/RESTAURANT-SERVICE/restaurant/fetchById/"+restaurantId;
//		// Make a REST call to the other service
//		return restTemplate.getForObject(otherServiceUrl, Restaurant.class);
		//return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/"+restaurantId, Restaurant.class);
		private static final String RESTAURANT_SERVICE_URL = "http://RESTAURANT-SERVICE/restaurant/fetchById/";

		public Restaurant fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
			try {
				return restTemplate.getForObject(RESTAURANT_SERVICE_URL + restaurantId, Restaurant.class);
			} catch (HttpClientErrorException.NotFound ex) {
				// Handle 404 Not Found error
				System.err.println("Restaurant with ID " + restaurantId + " not found.");
				return null; // Or throw an exception, or return a default value, depending on your requirements
			} catch (HttpClientErrorException ex) {
				// Handle other HTTP client errors
				System.err.println("HTTP Client Error: " + ex.getStatusCode() + " - " + ex.getStatusText());
				throw ex; // Or handle differently based on your application's requirements
			} catch (Exception ex) {
				// Handle other exceptions
				System.err.println("An error occurred: " + ex.getMessage());
				throw ex; // Or handle differently based on your application's requirements
			}
	}
	private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
		return foodItemRepo.findByRestaurantId(restaurantId);
	}

	public List<FoodItemDTO> findAllFoodItem() {
		List<FoodItem> foodItemListList = foodItemRepo.findAll();
		List<FoodItemDTO> foodItemDTOListDtoList = foodItemListList.stream().map(foodItem -> entityToDto(foodItem)).collect(Collectors.toList());
		return foodItemDTOListDtoList;
	}
}
