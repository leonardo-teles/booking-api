package com.booking.services;

import java.util.List;

import com.booking.exceptions.BookingException;
import com.booking.jsons.RestaurantRest;

public interface RestaurantService {

	RestaurantRest getRestaurantById(Long restaurantId) throws BookingException;
	
	public List<RestaurantRest> getRestaurants() throws BookingException;
}
