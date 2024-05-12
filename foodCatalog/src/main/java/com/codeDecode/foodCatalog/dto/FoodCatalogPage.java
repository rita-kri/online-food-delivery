package com.codeDecode.foodCatalog.dto;

import java.util.List;

import com.codeDecode.foodCatalog.entity.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodCatalogPage {
	
	private List<FoodItem> foodItemList;
	private Restaurant restaurant;

}
