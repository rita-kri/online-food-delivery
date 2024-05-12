package com.codeDecode.foodCatalog.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

    //to handle multiple instances for resturant MS, task of eureka to find the instance which is not so busy or avialabe to quickly response out of multiple instance we have to client so go to that particular instance and fetch our details.
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
