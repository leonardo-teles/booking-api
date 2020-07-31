package com.booking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.booking.exceptions.BookingException;
import com.booking.jsons.RestaurantRest;
import com.booking.responses.BookingResponse;
import com.booking.services.RestaurantService;

@RestController
@RequestMapping(path = "/booking" + "/v1")
public class RestaurantController {

	@Autowired
	RestaurantService restaurantService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "restaurant" + "/{" + "restaurantId" + "}", produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<RestaurantRest> getRestaurantById(@PathVariable Long restaurantId) throws BookingException {
		return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK", restaurantService.getRestaurantById(restaurantId));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<List<RestaurantRest>> getRestaurants() throws BookingException {
		return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK", restaurantService.getRestaurants());
	}
}
