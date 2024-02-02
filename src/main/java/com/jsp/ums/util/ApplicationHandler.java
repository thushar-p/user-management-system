package com.jsp.ums.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.ums.exception.UserNotFoundByIdException;

@RestControllerAdvice
public class ApplicationHandler extends ResponseEntityExceptionHandler{

	@Autowired
	public ResponseStructure<String> structure;


	public ResponseEntity<Object> structure(HttpStatus status, String message , Object rootCause){
		return new ResponseEntity<Object>(Map.of(
				"status", status.value(),
				"message", message,
				"root cause", rootCause				
				), status);
	}


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		List<ObjectError> allErrors = ex.getAllErrors();

		Map<String, String> errors = new HashMap<String,  String>();

		allErrors.forEach(error -> {

			FieldError fieldError = (FieldError) error;
			errors.put(fieldError.getField(), fieldError.getDefaultMessage());

		});

		return structure(HttpStatus.BAD_REQUEST, "Failed to save the data", errors);

	}



	@ExceptionHandler
	public ResponseEntity<Object> handleUserNotFoundByIdException(UserNotFoundByIdException exception){
		return structure(HttpStatus.NOT_FOUND, exception.getMessage(), "User Not found with specified Id");		
	}

}
