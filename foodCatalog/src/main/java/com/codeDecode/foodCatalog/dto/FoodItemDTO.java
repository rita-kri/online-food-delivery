package com.codeDecode.foodCatalog.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemDTO {
	
	private int id;
	private String itemName;
	private String itemDescrption;
	private boolean isVeg;
	private Number price;
	private Integer resturantId;
	
	@Column(nullable = false, columnDefinition = "INT DEFAULT 0")
	private Integer quantity;

}
