package com.boot.services;

import com.boot.exceptions.RestaurantExeception;
import com.boot.jsons.CreateReservationRest;
import com.boot.jsons.ReservationRest;

public interface ReservationService {

	ReservationRest getReservation(Long reservationId) throws RestaurantExeception;
	
	String createReservation(CreateReservationRest createReservationRest) throws RestaurantExeception;
}
