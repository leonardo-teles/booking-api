package com.boot.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.booking.controllers.CancelReservationController;
import com.booking.exceptions.BookingException;
import com.booking.responses.BookingResponse;
import com.booking.services.CancelReservationService;

public class CancelReservationControllerTest {
	
	private static final String LOCATOR = "Outback Steakhouse2";
	private static final String RESERVATION_DELETED = "LOCATOR_DELETED";
	
	private static final String SUCCESS_STATUS = "Success";
	private static final String SUCCESS_CODE = "200 OK";
	private static final String OK = "OK";

	@Mock
	CancelReservationService cancelReservationService;
	
	@InjectMocks
	CancelReservationController cancelReservationController;
	
	@Before
	public void init() throws BookingException {
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(cancelReservationService.deleteReservation(LOCATOR)).thenReturn(RESERVATION_DELETED);
	}
	
	@Test
	public void deleteReservationTest() throws BookingException {
		final BookingResponse<String> response = cancelReservationController.deleteReservation(LOCATOR);
		
		assertEquals(response.getStatus(), SUCCESS_STATUS);
		assertEquals(response.getCode(), SUCCESS_CODE);
		assertEquals(response.getMessage(), OK);
		assertEquals(response.getData(), RESERVATION_DELETED);
	}
}
