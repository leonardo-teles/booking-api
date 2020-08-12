package com.booking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.booking.exceptions.BookingException;
import com.booking.jsons.CreateReservationRest;
import com.booking.jsons.ReservationRest;
import com.booking.responses.BookingResponse;
import com.booking.services.ReservationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/booking" + "/v1")
public class ReservationController {

	@Autowired
	ReservationService reservationService;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "reservation" + "/{" + "reservationId" + "}", produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<ReservationRest> getReservationById(@PathVariable Long reservationId) throws BookingException {
		return new BookingResponse<ReservationRest>("Success", String.valueOf(HttpStatus.OK), "OK", reservationService.getReservationById(reservationId));
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
	public BookingResponse<String> createReservation(@RequestBody CreateReservationRest createReservationRest) throws BookingException {
		return new BookingResponse<>("Success", String.valueOf(HttpStatus.OK), "OK", reservationService.createReservation(createReservationRest));
	}
}
