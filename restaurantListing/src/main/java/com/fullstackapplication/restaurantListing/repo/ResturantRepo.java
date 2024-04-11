package com.fullstackapplication.restaurantListing.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullstackapplication.restaurantListing.Entity.Resturant;

@Repository
public interface ResturantRepo extends JpaRepository<Resturant, Integer>{

}
