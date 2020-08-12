package com.boot.controller;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.booking.controllers.ReservationController;
import com.booking.exceptions.BookingException;
import com.booking.jsons.CreateReservationRest;
import com.booking.jsons.ReservationRest;
import com.booking.responses.BookingResponse;
import com.booking.services.ReservationService;

public class ReservationControllerTest {
	
	private static final Long RESERVATION_ID = 1L;
	
	private static final Long RESTAURANT_ID = 1L;
	private static final Date DATE = new Date();
	private static final Long PERSON = 1L;
	private static final Long TURN_ID = 1L;
	private static final String LOCATOR = "Outback 01";
	
	private static final String SUCCESS_STATUS = "Success";
	private static final String SUCCESS_CODE = "200 OK";
	private static final String OK = "OK";
	
	public static final ReservationRest RESERVATION_REST = new ReservationRest();
	
	CreateReservationRest CREATE_RESERVATION_REST = new CreateReservationRest();

	@Mock
	ReservationService reservationService;
	
	@InjectMocks
	ReservationController reservationController;
	
	@Before
	public void init() throws BookingException {
		MockitoAnnotations.initMocks(this);
		
		CREATE_RESERVATION_REST.setDate(DATE);
		CREATE_RESERVATION_REST.setPerson(PERSON);
		CREATE_RESERVATION_REST.setRestaurantId(RESTAURANT_ID);
		CREATE_RESERVATION_REST.setTurnId(TURN_ID);
		
		Mockito.when(reservationService.getReservationById(RESERVATION_ID)).thenReturn(RESERVATION_REST);
		Mockito.when(reservationService.createReservation(CREATE_RESERVATION_REST)).thenReturn(LOCATOR);
	}	
	
	@Test
	public void getReservationByIdTest() throws BookingException {
		final BookingResponse<ReservationRest> response = reservationController.getReservationById(RESERVATION_ID);
		
		assertEquals(response.getStatus(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), RESERVATION_REST);
	}
	
	@Test
	public void createReservationTest() throws BookingException {
		BookingResponse<String> response = reservationController.createReservation(CREATE_RESERVATION_REST);
		
		assertEquals(response.getStatus(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), LOCATOR);

	}
}
