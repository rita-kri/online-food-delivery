package com.codeDecode.order.service;

import com.codeDecode.order.dto.OrderDTO;
import com.codeDecode.order.dto.OrderDTOFromFE;
import com.codeDecode.order.dto.UserDTO;
import com.codeDecode.order.entity.Order;
import com.codeDecode.order.repo.OrderRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    SequenceGenerator sequenceGenerator;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ModelMapper modelMapper;

    public Order dtoToEntity(OrderDTO orderDTO){
     Order mapToEntity = modelMapper.map(orderDTO, Order.class);
     return mapToEntity;
    }

    public OrderDTO entityToDto(Order order){
        OrderDTO mapToDto =  modelMapper.map(order, OrderDTO.class);
        return  mapToDto;
    }

    public OrderDTO saveOrderInDB(OrderDTOFromFE orderDetails) {
        Integer newOrderId = sequenceGenerator.generatorNextOrderId();
        UserDTO userDTO = fetchUserDetailsFromUserId(orderDetails.getUserId());
        Order orderToBeSaved = new Order(newOrderId, orderDetails.getFoodItemList(),orderDetails.getRestaurant(),userDTO);
        orderRepo.save(orderToBeSaved);
        return this.entityToDto(orderToBeSaved);
    }

    private UserDTO fetchUserDetailsFromUserId(Integer userId) {
    return restTemplate.getForObject("http://USER-SERVICE/user/fetchById/"+userId, UserDTO.class);
    }
}
