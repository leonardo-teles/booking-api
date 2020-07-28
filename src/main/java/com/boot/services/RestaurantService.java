package com.boot.services;

import java.util.List;

import com.boot.exceptions.RestaurantExeception;
import com.boot.jsons.RestaurantRest;

public interface RestaurantService {

	RestaurantRest getRestaurantById(Long restaurantId) throws RestaurantExeception;
	
	public List<RestaurantRest> getRestaurants() throws RestaurantExeception;
}
