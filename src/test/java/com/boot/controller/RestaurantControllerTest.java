package com.boot.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.booking.controllers.RestaurantController;
import com.booking.exceptions.BookingException;
import com.booking.jsons.RestaurantRest;
import com.booking.responses.BookingResponse;
import com.booking.services.RestaurantService;

public class RestaurantControllerTest {

	private static final Long RESTAURANT_ID = 1L;
	private static final String SUCCESS_STATUS = "Success";
	private static final String SUCCESS_CODE = "200 OK";
	private static final String OK = "OK";
	
	@Mock
	RestaurantService restaurantService;
	
	@InjectMocks
	RestaurantController restaurantController;
	
	@Before
	public void init() throws BookingException {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getRestaurantByIdTest() throws BookingException {
		final BookingResponse<RestaurantRest> response = restaurantController.getRestaurantById(RESTAURANT_ID);
		assertEquals(response.getStatus(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);
		assertEquals(response.getMessage(), OK);
		//assertEquals(response.getData(), RESTAURANT_REST);
		
	}
}
