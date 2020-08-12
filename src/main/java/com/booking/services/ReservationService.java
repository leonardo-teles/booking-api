package com.booking.services;

import com.booking.exceptions.BookingException;
import com.booking.jsons.CreateReservationRest;
import com.booking.jsons.ReservationRest;

public interface ReservationService {

	ReservationRest getReservationById(Long reservationId) throws BookingException;
	
	String createReservation(CreateReservationRest createReservationRest) throws BookingException;
}
