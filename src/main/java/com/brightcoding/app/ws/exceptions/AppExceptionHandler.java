package com.brightcoding.app.ws.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Object> HandlerMethodArgumentNotValid(MethodArgumentNotValidException ex ,WebRequest request){
		
		Map<String, String> errors=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(),error.getDefaultMessage()   ));
		
		
		
		return new ResponseEntity<>(errors,new HttpHeaders(),HttpStatus.BAD_REQUEST);
	}
	
}
