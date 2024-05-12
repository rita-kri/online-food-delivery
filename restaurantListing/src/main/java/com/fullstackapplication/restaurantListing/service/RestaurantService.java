package com.fullstackapplication.restaurantListing.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.fullstackapplication.restaurantListing.DTO.RestaurantDTO;
import com.fullstackapplication.restaurantListing.Entity.Restaurant;
import com.fullstackapplication.restaurantListing.repo.RestaurantRepo;

@Service
public class RestaurantService {
	
	@Autowired
	private RestaurantRepo restaurantRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public List<RestaurantDTO> findAllRestaurants(){
		List<Restaurant> restaurants = restaurantRepo.findAll(); // this ORM(english language) so we need to convert into select means datbase language we use Dialect so it will convert our ORM language to db languages.
		//map it to list of DTO
		List<RestaurantDTO> listOfRestaurantDtos = restaurants.stream().map(restaurant -> this.restaurantToDTO(restaurant)).collect(Collectors.toList());
		return listOfRestaurantDtos;
	}
	
	public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
		Restaurant dtoToRestaurant = this.dtoToRestaurant(restaurantDTO);
		Restaurant saveResturant = this.restaurantRepo.save(dtoToRestaurant);
		return this.restaurantToDTO(saveResturant);
	}
	
	public ResponseEntity<RestaurantDTO> getRestaurantById(Integer id) {
		Optional<Restaurant> restaurantById = this.restaurantRepo.findById(id);
			if(restaurantById.isPresent()) {
				return new ResponseEntity<RestaurantDTO>(restaurantToDTO(restaurantById.get()), HttpStatus.OK);
			}
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	public Restaurant dtoToRestaurant(RestaurantDTO restaurantDTO) {
		Restaurant restaurant = this.modelMapper.map(restaurantDTO, Restaurant.class);
		
//		resturant.setId(resturantDTO.getId());
//		resturant.setName(resturantDTO.getName());
//		resturant.setAddress(resturantDTO.getAddress());
//		resturant.setCity(resturantDTO.getCity());
//		resturant.setRestrurantDescription(resturantDTO.getResturantDescription());

		return restaurant;
	}
	
	public RestaurantDTO restaurantToDTO(Restaurant restaurant) {
		RestaurantDTO restaurantDto = this .modelMapper.map(restaurant, RestaurantDTO.class);
		
//		resturantDto.setId(resturant.getId());
//		resturantDto.setName(resturant.getName());
//		resturantDto.setAddress(resturant.getAddress());
//		resturantDto.setCity(resturant.getCity());
//		resturantDto.setRestrurantDescription(resturant.getResturantDescription());
		
		return restaurantDto;
	}
	

}
