package com.boot.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.boot.entities.Restaurant;
import com.boot.exceptions.NotFoundException;
import com.boot.exceptions.RestaurantExeception;
import com.boot.jsons.RestaurantRest;
import com.boot.repositories.RestaurantRepository;
import com.boot.services.RestaurantService;

public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	public static final ModelMapper modelMapper = new ModelMapper();
	
	public RestaurantRest getRestaurantById(Long restaurantId) throws RestaurantExeception {
		return modelMapper.map(getRestaurantEntity(restaurantId), RestaurantRest.class);
	}
	
	private Restaurant getRestaurantEntity(Long restaurantId) throws RestaurantExeception {
		return restaurantRepository.findById(restaurantId).orElseThrow(() -> new NotFoundException("SNOT-404-1", "RESTAURANT_NOT_FOUND"));
	}
}