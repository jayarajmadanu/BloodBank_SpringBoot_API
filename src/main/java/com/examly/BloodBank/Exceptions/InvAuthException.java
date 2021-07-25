package com.examly.BloodBank.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvAuthException extends RuntimeException{
	
	public InvAuthException(String msg) {
		super(msg);
	}

}
