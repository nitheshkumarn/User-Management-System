package com.jsp.usm.User_Management.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.jsp.usm.User_Management.exception.UserInsertionFailedException;
import com.jsp.usm.User_Management.exception.UserNotFoundByIdException;


@RestControllerAdvice
public class ApplicationHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<ObjectError> allErrors =ex.getAllErrors();
		
		Map<String,String> errors = new HashMap<String,String>();
		allErrors.forEach(error ->{
		FieldError fieldError = (FieldError) error; //downcasting objecterror to its child fielderror
		errors.put(fieldError.getField(), fieldError.getDefaultMessage());
		});
		return structure(HttpStatus.BAD_REQUEST, "Failed to save the data", errors);
	}
	
	private ResponseEntity<Object> structure(HttpStatus status, String message, Object rootCause){
		return new ResponseEntity<Object>(Map.of(
				"status", status.value(),
				"message", message,
				"rootCause", rootCause
				), status);
		
		// or  create a new Response structure with rootcause as the variable.
				
	}

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> UserInsertionFailedExceptionHandler(UserInsertionFailedException exception){
		ResponseStructure<String> rs = new ResponseStructure<String>();
		rs.setStatus(HttpStatus.NOT_FOUND.value());
		rs.setMessage(exception.getMessage());
		rs.setData("User not found with the specified ID");// replace data with rootcause in a new structure class
		
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<Object> handleUserNotFoundById(UserNotFoundByIdException exception){
		
		return structure(HttpStatus.BAD_REQUEST, exception.getMessage(), "User is not present with the given Id");
	}
}
