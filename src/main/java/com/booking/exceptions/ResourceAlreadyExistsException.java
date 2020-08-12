package com.booking.exceptions;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import com.booking.dtos.ErrorDTO;

public class ResourceAlreadyExistsException extends BookingException {
	private static final long serialVersionUID = 1L;

	public ResourceAlreadyExistsException(String code, String message) {
		super(code, HttpStatus.ALREADY_REPORTED.value(), message);
	}

	public ResourceAlreadyExistsException(String code, int responseCode, String message, ErrorDTO data) {
		super(code, HttpStatus.ALREADY_REPORTED.value(), message, Arrays.asList(data));
	}

}
