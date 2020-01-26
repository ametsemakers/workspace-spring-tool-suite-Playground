package com.example.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="L'entreprise n'a pas pu être validé")
public class EntrepriseValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
}
