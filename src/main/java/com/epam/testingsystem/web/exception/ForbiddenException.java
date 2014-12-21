package com.epam.testingsystem.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "You do not have privileges to access this page!")
public class ForbiddenException extends RuntimeException {
	public ForbiddenException() {
		super("You do not have privileges to access this page!");
	}
}
