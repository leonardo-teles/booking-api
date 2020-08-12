package com.boot.service;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.booking.entities.Reservation;
import com.booking.entities.Restaurant;
import com.booking.entities.Turn;
import com.booking.exceptions.BookingException;
import com.booking.jsons.CreateReservationRest;
import com.booking.repositories.ReservationRepository;
import com.booking.repositories.RestaurantRepository;
import com.booking.repositories.TurnRepository;
import com.booking.services.impl.ReservationServiceImpl;

public class ReservationServiceTest {
	
	private static final Long RESERVATION_ID = 1L;
	
	private static final Long RESTAURANT_ID = 1L;
	private static final Date DATE = new Date();
	private static final Long PERSON = 30L;
	private static final Long TURN_ID = 5L;
	
	private static final String TURN_NAME = "TURNO_11_00";
	
	private static final Restaurant RESTAURANT = new Restaurant();
	private static final Optional<Restaurant> OPTIONAL_RESTAURANT = Optional.of(RESTAURANT);
	private static final Optional<Restaurant> OPTIONAL_RESTAURANT_EMPTY = Optional.empty();

	private static final Turn TURN = new Turn();
	private static final List<Turn> TURN_LIST = new ArrayList<>();
	private static final Optional<Turn> OPTIONAL_TURN = Optional.of(TURN);
	private static final Optional<Turn> OPTIONAL_TURN_EMPTY = Optional.empty();
	
	CreateReservationRest CREATE_RESERVATION_REST = new CreateReservationRest();
	public static final Reservation RESERVATION = new Reservation();
	
	private static final Optional<Reservation> OPTIONAL_RESERVATION_EMPTY = Optional.empty();
	private static final Optional<Reservation> OPTIONAL_RESERVATION = Optional.of(RESERVATION);
	
	@Mock
	private RestaurantRepository restaurantRepository;
	
	@Mock
	private TurnRepository turnRepository;
	
	@Mock
	private ReservationRepository reservationRepository;

	@InjectMocks
	private ReservationServiceImpl reservationService;
	
	@Before
	public void init() throws BookingException {
		MockitoAnnotations.initMocks(this);
		
		RESTAURANT.setId(RESTAURANT_ID);
		RESTAURANT.setTurns(TURN_LIST);
		
		TURN.setId(TURN_ID);
		TURN.setRestaurant(RESTAURANT);
		
		CREATE_RESERVATION_REST.setDate(DATE);
		CREATE_RESERVATION_REST.setPerson(PERSON);
		CREATE_RESERVATION_REST.setRestaurantId(RESTAURANT_ID);
		CREATE_RESERVATION_REST.setTurnId(TURN_ID);
		
		RESERVATION.setId(RESERVATION_ID);
		RESERVATION.setRestaurant(RESTAURANT);
		RESERVATION.setTurn(TURN_NAME);
	}
	 
	@Test
	public void getReservationByIdTest() throws BookingException {
		Mockito.when(reservationRepository.findById(RESERVATION_ID)).thenReturn(Optional.of(RESERVATION));
		
		reservationService.getReservationById(RESERVATION_ID);
	}

	@Test
	public void createReservationTest() throws BookingException {
		Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT);
		Mockito.when(turnRepository.findById(TURN_ID)).thenReturn(OPTIONAL_TURN);
		Mockito.when(reservationRepository.findByTurnAndRestaurantId(TURN.getName(), RESTAURANT.getId())).thenReturn(OPTIONAL_RESERVATION_EMPTY);
		Mockito.when(reservationRepository.save(Mockito.any(Reservation.class))).thenReturn(new Reservation());
		
		reservationService.createReservation(CREATE_RESERVATION_REST);
	}
	
	@Test(expected = BookingException.class)
	public void createReservationRestaurantFindByIdTestError() throws BookingException {
		Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT_EMPTY);
		
		reservationService.createReservation(CREATE_RESERVATION_REST);
		fail();	
	}
	
	@Test(expected = BookingException.class)
	public void createReservationTurnFindByIdTestError() throws BookingException {
		Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT);
		Mockito.when(turnRepository.findById(TURN_ID)).thenReturn(OPTIONAL_TURN_EMPTY);
		
		reservationService.createReservation(CREATE_RESERVATION_REST);
		fail();	
	}
	
	@Test(expected = BookingException.class)
	public void createReservationTurnAndRestaurantTestError() throws BookingException {
		Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT);
		Mockito.when(turnRepository.findById(TURN_ID)).thenReturn(OPTIONAL_TURN);
		Mockito.when(reservationRepository.findByTurnAndRestaurantId(TURN.getName(), RESTAURANT.getId())).thenReturn(OPTIONAL_RESERVATION);
		
		reservationService.createReservation(CREATE_RESERVATION_REST);
		fail();	
	}

	@Test(expected = BookingException.class)
	public void createReservationInternalServerErrorTest() throws BookingException {
		Mockito.when(restaurantRepository.findById(RESTAURANT_ID)).thenReturn(OPTIONAL_RESTAURANT);
		Mockito.when(turnRepository.findById(TURN_ID)).thenReturn(OPTIONAL_TURN);
		Mockito.when(reservationRepository.findByTurnAndRestaurantId(TURN.getName(), RESTAURANT.getId())).thenReturn(OPTIONAL_RESERVATION_EMPTY);
		
		Mockito.doThrow(Exception.class).when(reservationRepository).save(Mockito.any(Reservation.class));
		
		reservationService.createReservation(CREATE_RESERVATION_REST);
		fail();	
	}
	
}
