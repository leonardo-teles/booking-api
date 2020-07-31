package com.booking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.booking.exceptions.BookingException;
import com.booking.responses.BookingResponse;
import com.booking.services.CancelReservationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/booking" + "/v1")
public class CancelReservationController {

	@Autowired
	CancelReservationService cancelReservationService;
	
	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping(value = "/deleteReservation", produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<String> deleteReservation(@RequestParam String locator) throws BookingException {
		return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK", cancelReservationService.deleteReservation(locator));
	}
}
