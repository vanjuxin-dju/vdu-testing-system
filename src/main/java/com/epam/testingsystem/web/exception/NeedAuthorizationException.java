package com.epam.testingsystem.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Need authorization!")
public class NeedAuthorizationException extends RuntimeException {
	public NeedAuthorizationException() {
		super("Need authorization!");
	}
}
