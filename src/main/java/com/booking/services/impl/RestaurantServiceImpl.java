package com.booking.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.entities.Restaurant;
import com.booking.exceptions.BookingException;
import com.booking.exceptions.NotFoundException;
import com.booking.jsons.RestaurantRest;
import com.booking.repositories.RestaurantRepository;
import com.booking.services.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	public static final ModelMapper MODEL_MAPPER = new ModelMapper();

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	public RestaurantRest getRestaurantById(Long restaurantId) throws BookingException {
		return MODEL_MAPPER.map(getRestaurantEntity(restaurantId), RestaurantRest.class);
	}
	
	public List<RestaurantRest> getRestaurants() throws BookingException {
		
		final List<Restaurant> restaurantsEntity = restaurantRepository.findAll();
		
		return restaurantsEntity.stream().map(service -> MODEL_MAPPER.map(service, RestaurantRest.class)).collect(Collectors.toList());
	}
	
	private Restaurant getRestaurantEntity(Long resturantId) throws BookingException {
		return restaurantRepository.findById(resturantId).orElseThrow(() -> new NotFoundException("SNOT-404-1", "RESTAURANT_NOT_FOUND"));
	}
}
