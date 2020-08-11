package com.boot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.booking.entities.Board;
import com.booking.entities.Reservation;
import com.booking.entities.Restaurant;
import com.booking.entities.Turn;
import com.booking.exceptions.BookingException;
import com.booking.repositories.RestaurantRepository;
import com.booking.services.impl.RestaurantServiceImpl;

public class RestaurantServiceTest {
	
	private static final Long RESTAURANT_ID = 1L;
	private static final String NAME = "Outback";
	private static final String DESCRIPTION = "Comida Australiana";
	private static final String ADDRES = "Av. das Américas";
	private static final String IMAGE = "www.image.com";

	public static final Restaurant RESTAURANT = new Restaurant();
	public static final List<Turn> TURN_REST = new ArrayList<>();
	public static final List<Board> BOARD_REST = new ArrayList<>();
	public static final List<Reservation> RESERVATION_REST = new ArrayList<>();

	
	@Mock
	RestaurantRepository restaurantRepository;
	
	@InjectMocks
	RestaurantServiceImpl restaurantService;
	
	@Before
	public void init() throws BookingException {
		MockitoAnnotations.initMocks(this);
		
		RESTAURANT.setId(RESTAURANT_ID);
		RESTAURANT.setName(NAME);
		RESTAURANT.setDescription(DESCRIPTION);
		RESTAURANT.setAddress(ADDRES);
		RESTAURANT.setImage(IMAGE);
		RESTAURANT.setTurns(TURN_REST);
		RESTAURANT.setBoards(BOARD_REST);
		RESTAURANT.setReservations(RESERVATION_REST);
	}
	
	@Test
	public void getRestaurantByIdTest() throws BookingException {
		Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(Optional.of(RESTAURANT));
		
		restaurantService.getRestaurantById(RESTAURANT_ID);
	}
}