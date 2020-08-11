package com.boot.controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.booking.controllers.RestaurantController;
import com.booking.exceptions.BookingException;
import com.booking.services.RestaurantService;

public class RestaurantControllerTest {

	@Mock
	RestaurantService restaurantService;
	
	@InjectMocks
	RestaurantController restaurantController;
	
	public void init() throws BookingException {
		MockitoAnnotations.initMocks(this);
	}
}
