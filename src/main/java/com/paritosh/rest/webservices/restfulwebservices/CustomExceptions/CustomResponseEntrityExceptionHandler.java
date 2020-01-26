package com.paritosh.rest.webservices.restfulwebservices.CustomExceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController//
public class CustomResponseEntrityExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
 	public final ResponseEntity<Object> handleAllExeption(Exception ex, WebRequest request){
 		
		ExceptionResponse res = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity(res,HttpStatus.INTERNAL_SERVER_ERROR);
 	}
	
	@ExceptionHandler(UserNotFoundException.class)
 	public final ResponseEntity<Object> handleUserNotFoundExeption(UserNotFoundException ex, WebRequest request){
 		
		ExceptionResponse res = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity(res,HttpStatus.NOT_FOUND);//
 	}
@Override
protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatus status, WebRequest request) {
	// TODO Auto-generated method stub
	ExceptionResponse es = new ExceptionResponse(new Date(), "Validation Failed", ex.getBindingResult().toString());
	
	return new ResponseEntity<>(es , HttpStatus.BAD_REQUEST);
}
}
