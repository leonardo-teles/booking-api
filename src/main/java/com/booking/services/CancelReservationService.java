package com.booking.services;

import com.booking.exceptions.BookingException;

public interface CancelReservationService {

	public String deleteReservation(String locator) throws BookingException;
}
