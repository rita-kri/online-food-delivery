package com.codeDecode.foodCatalog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String itemName;
	private String itemDescrption;
	private boolean isVeg;
	private Number price;
	private Integer resturantId;
	
	@Column(nullable = false, columnDefinition = "INT DEFAULT 0")
	private Integer quantity;
	
}
