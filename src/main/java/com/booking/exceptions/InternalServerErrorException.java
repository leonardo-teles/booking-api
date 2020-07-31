package com.booking.exceptions;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import com.booking.dtos.ErrorDTO;

public class InternalServerErrorException extends BookingException {

	private static final long serialVersionUID = 1L;

	public InternalServerErrorException(String code, String message) {
		super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
	}
	
	public InternalServerErrorException(String code, String message, ErrorDTO data) {
		super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(), message, Arrays.asList(data));
	}
}
