package com.booking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booking.exceptions.BookingException;
import com.booking.jsons.RestaurantRest;
import com.booking.responses.BookingResponse;
import com.booking.services.RestaurantService;

@RestController
@RequestMapping(path = "/booking" + "v1")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
	public BookingResponse<RestaurantRest> getRestaurantById(Long restaurantId) throws BookingException {
		return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK", restaurantService.getRestaurantById(restaurantId));
	}
}
