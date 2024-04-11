package com.codeDecode.foodCatalog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FoodCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodCatalogApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
		}
	@Bean
	@LoadBalanced   //to handle multiple instances for resturant MS, task of eureka to find the instance which is not so busy or avialabe to quickly response out of multiple instance we have to client so go to that particular instance and fetch our details.
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
