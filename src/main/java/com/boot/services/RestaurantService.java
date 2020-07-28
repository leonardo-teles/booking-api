package com.boot.services;

import com.boot.exceptions.RestaurantExeception;
import com.boot.jsons.RestaurantRest;

public interface RestaurantService {

	RestaurantRest getRestaurantById(Long restaurantId) throws RestaurantExeception;
}
