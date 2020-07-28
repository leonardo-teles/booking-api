package com.boot.exceptions;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import com.boot.dtos.ErrorDTO;

public class NotFoundException extends RestaurantExeception {
	private static final long serialVersionUID = 1L;

	public NotFoundException(String code, String message) {
		super(code, HttpStatus.NOT_FOUND.value(), message);
	}
	
	public NotFoundException(String code, String message, ErrorDTO data) {
		super(code, HttpStatus.NOT_FOUND.value(), message, Arrays.asList(data));
	}
}
