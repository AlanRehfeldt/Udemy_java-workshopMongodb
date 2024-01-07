package com.rehfeldt.workshopmongo.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rehfeldt.workshopmongo.services.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		String error = "Não encontrado";
		String message = e.getMessage();
		String path = request.getRequestURI();
		
		StandardError err = new StandardError(System.currentTimeMillis(), status.value(), error, message, path);
		
		return ResponseEntity.status(status).body(err);
	}
	
}
