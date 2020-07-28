package com.boot.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.boot.entities.Reservation;
import com.boot.entities.Restaurant;
import com.boot.entities.Turn;
import com.boot.exceptions.NotFoundException;
import com.boot.exceptions.RestaurantExeception;
import com.boot.jsons.CreateReservationRest;
import com.boot.jsons.ReservationRest;
import com.boot.repositories.RestaurantRepository;
import com.boot.repositories.TurnRepository;
import com.boot.services.ReservationService;

public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private TurnRepository turnRepository;

	public ReservationRest getReservation(Long reservationId) throws RestaurantExeception {
		return null;
	}

	public String createReservation(CreateReservationRest createReservationRest) throws RestaurantExeception {
		
		final Restaurant restaurantId = restaurantRepository.findById(createReservationRest.getRestaurantId())
				.orElseThrow(() -> new NotFoundException("RESTAURANT_NOT_FOUND", "RESTAURANT_NOT_FOUND"));

		final Turn turnId = turnRepository.findById(createReservationRest.getTurnId())
				.orElseThrow(() -> new NotFoundException("TURN_NOT_FOUND", "TURN_NOT_FOUND"));
		
		String locator = generateLocator(restaurantId, createReservationRest);
		
		final Reservation reservation = new Reservation();
		reservation.setLocator(locator);
		reservation.setPerson(createReservationRest.getPerson());
		reservation.setDate(createReservationRest.getDate());
		reservation.setRestaurant(restaurantId);
		reservation.setTurn(turnId.getName());
		
		return null;
	}
	
	private String generateLocator(Restaurant restaurantId, CreateReservationRest createReservationRest) throws RestaurantExeception {
		return restaurantId.getName() + createReservationRest.getTurnId();
	}

}
