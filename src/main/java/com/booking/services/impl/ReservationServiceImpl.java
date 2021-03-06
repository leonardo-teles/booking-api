package com.booking.services.impl;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.entities.Reservation;
import com.booking.entities.Restaurant;
import com.booking.entities.Turn;
import com.booking.exceptions.BookingException;
import com.booking.exceptions.InternalServerErrorException;
import com.booking.exceptions.NotFoundException;
import com.booking.exceptions.ResourceAlreadyExistsException;
import com.booking.jsons.CreateReservationRest;
import com.booking.jsons.ReservationRest;
import com.booking.repositories.ReservationRepository;
import com.booking.repositories.RestaurantRepository;
import com.booking.repositories.TurnRepository;
import com.booking.services.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);
	
	public static final ModelMapper MODEL_MAPPER = new ModelMapper();

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private TurnRepository turnRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	public ReservationRest getReservationById(Long reservationId) throws BookingException {
		return MODEL_MAPPER.map(getReservationEntity(reservationId), ReservationRest.class);
	}

	public String createReservation(CreateReservationRest createReservationRest) throws BookingException {
		
		final Restaurant restaurantId = restaurantRepository.findById(createReservationRest.getRestaurantId())
				.orElseThrow(() -> new NotFoundException("RESTAURANT_NOT_FOUND", "RESTAURANT_NOT_FOUND")); 

		final Turn turn = turnRepository.findById(createReservationRest.getTurnId())
				.orElseThrow(() -> new NotFoundException("TURN_NOT_FOUND", "TURN_NOT_FOUND")); 
		
		if (reservationRepository.findByTurnAndRestaurantId(turn.getName(), restaurantId.getId()).isPresent()) {
			throw new ResourceAlreadyExistsException("RESERVATION_ALREADY_EXISTS", "RESERVATION_ALREADY_EXISTS");
		}
		
		String locator = generateLocator(restaurantId, createReservationRest);
		
		final Reservation reservation = new Reservation();
		reservation.setLocator(locator);
		reservation.setPerson(createReservationRest.getPerson());
		reservation.setDate(createReservationRest.getDate());
		reservation.setRestaurant(restaurantId);
		reservation.setTurn(turn.getName());
		
		try {
			reservationRepository.save(reservation);
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR", e);
			
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		
		return locator;
	}
	
	private String generateLocator(Restaurant restaurantId, CreateReservationRest createReservationRest) throws BookingException {
		return restaurantId.getName() + createReservationRest.getTurnId();
	}
	
	private Reservation getReservationEntity(Long reservationId) throws BookingException {
		return reservationRepository.findById(reservationId).orElseThrow(() -> new NotFoundException("SNOT-404-1", "RESERVATION_NOT_FOUND"));
	}
	
}
