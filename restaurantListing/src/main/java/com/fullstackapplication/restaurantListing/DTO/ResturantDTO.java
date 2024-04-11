package com.fullstackapplication.restaurantListing.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResturantDTO {
	
	private int id;
	private String name;
	private String address;
	private String city;
	private String resturantDescription;

}
