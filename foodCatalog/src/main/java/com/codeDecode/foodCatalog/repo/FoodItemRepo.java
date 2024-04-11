package com.codeDecode.foodCatalog.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeDecode.foodCatalog.entity.FoodItem;

@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem, Integer>{

	List<FoodItem> findByResturantId(Integer resturantId);

}
