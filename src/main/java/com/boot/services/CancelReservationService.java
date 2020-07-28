package com.boot.services;

import com.boot.exceptions.RestaurantExeception;

public interface CancelReservationService {

	public String deleteReservation(String locator) throws RestaurantExeception;
}
