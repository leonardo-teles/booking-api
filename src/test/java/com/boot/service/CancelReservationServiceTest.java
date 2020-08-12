package com.boot.service;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.booking.entities.Reservation;
import com.booking.exceptions.BookingException;
import com.booking.repositories.ReservationRepository;
import com.booking.services.impl.CancelReservationServiceImpl;

public class CancelReservationServiceTest {
	
	private static final String LOCATOR = "Outback Steakhouse2";
	private static final String RESERVATION_DELETED = "LOCATOR_DELETED";
	
	private static final Reservation RESERVATION = new Reservation();
	
	private static final Optional<Reservation> OPTIONAL_RESERVATION_EMPTY = Optional.empty();
	private static final Optional<Reservation> OPTIONAL_RESERVATION = Optional.of(RESERVATION);

	@Mock
	private ReservationRepository reservationRepository;
	
	@InjectMocks
	private CancelReservationServiceImpl cancelReservationService;
	
	@Before
	public void init() throws BookingException {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void deleteReservationTest() throws BookingException {
		Mockito.when(reservationRepository.findByLocator(LOCATOR)).thenReturn(OPTIONAL_RESERVATION);
		Mockito.when(reservationRepository.deleteByLocator(LOCATOR)).thenReturn(OPTIONAL_RESERVATION);
		
		final String response = cancelReservationService.deleteReservation(LOCATOR);
		assertEquals(response, RESERVATION_DELETED);
	}
	
	@Test(expected = BookingException.class)
	public void deleteReservationNotFoundErrorTest() throws BookingException {
		Mockito.when(reservationRepository.findByLocator(LOCATOR)).thenReturn(OPTIONAL_RESERVATION_EMPTY);
		Mockito.when(reservationRepository.deleteByLocator(LOCATOR)).thenReturn(OPTIONAL_RESERVATION);
		
		final String response = cancelReservationService.deleteReservation(LOCATOR);
		assertEquals(response, RESERVATION_DELETED);
		fail();
	}

	@Test(expected = BookingException.class)
	public void deleteReservationINternalServerErrorTest() throws BookingException {
		Mockito.when(reservationRepository.findByLocator(LOCATOR)).thenReturn(OPTIONAL_RESERVATION);
		Mockito.doThrow(Exception.class).when(reservationRepository).deleteByLocator(LOCATOR);
		
		final String response = cancelReservationService.deleteReservation(LOCATOR);
		assertEquals(response, RESERVATION_DELETED);
		fail();
	}
}
