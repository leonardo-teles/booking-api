package com.booking.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.exceptions.BookingException;
import com.booking.exceptions.InternalServerErrorException;
import com.booking.exceptions.NotFoundException;
import com.booking.repositories.ReservationRepository;
import com.booking.services.CancelReservationService;

@Service
public class CancelReservationServiceImpl implements CancelReservationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CancelReservationServiceImpl.class);

	@Autowired
	private ReservationRepository reservationRepository;
	
	public String deleteReservation(String locator) throws BookingException {
		
		reservationRepository.findByLocator(locator).orElseThrow(() -> new NotFoundException("LOCATOR_NOT_FOUND", "LOCATOR_NOT_FOUND")); 
		
		try {
			reservationRepository.deleteByLocator(locator);
		} catch (final Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR", e);
			
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
		}
		
		return "LOCATOR_DELETED";
	}

}
