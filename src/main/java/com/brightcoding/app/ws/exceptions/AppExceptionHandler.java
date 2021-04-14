package com.brightcoding.app.ws.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.brightcoding.app.ws.responses.ErrorUserClass;

@ControllerAdvice
public class AppExceptionHandler {
	
	@ExceptionHandler(value = UserException.class)
	public ResponseEntity<Object> HandlerUserException(UserException ex,WebRequest request){
		ErrorUserClass exceptionMessage = new ErrorUserClass(new Date(), ex.getMessage()); 
		return new ResponseEntity<>(exceptionMessage,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> HandlerException(Exception ex,WebRequest request){
		ErrorUserClass exceptionMessage = new ErrorUserClass(new Date(), ex.getMessage()); 
		return new ResponseEntity<>(exceptionMessage,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
