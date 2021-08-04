package com.mphasis.books.exception;


import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.mphasis.books.dto.ErrorMessage;

/*
 * This class behaves as global exception handler for MphasisBookApplication.
 */

@RestControllerAdvice
public class ExceptionControllerAdvice{
	
	//Auto wiring Properties bean (defined at com.mphasis.books/MphasisBooksConfig.java)
	@Autowired
	Properties properties;
	
	//To handle invalid book id exception (when book id requested is not numeric)
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorMessage> invalidBookIdExceptionHandler(MethodArgumentTypeMismatchException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(properties.getProperty("invalidBookId").toString());
		return new ResponseEntity<ErrorMessage>(error,HttpStatus.BAD_REQUEST);
	}
	
	//To handle no books found exception
	@ExceptionHandler(NoBookFoundException.class)
	public ResponseEntity<ErrorMessage> noBookFoundExceptionHandler(NoBookFoundException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setMessage(properties.getProperty("noBookFound").toString());
		return new ResponseEntity<ErrorMessage>(error,HttpStatus.NOT_FOUND);
	}	
	
	//To Handle General Exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> exceeptionHandler(Exception ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(properties.getProperty("generalExceptionMessage").toString());
		return new ResponseEntity<ErrorMessage>(error,HttpStatus.BAD_REQUEST);
	}
	
}
