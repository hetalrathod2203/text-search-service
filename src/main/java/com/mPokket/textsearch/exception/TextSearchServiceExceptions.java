package com.mPokket.textsearch.exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.mPokket.textsearch.model.Response;
import com.mPokket.textsearch.util.Constants;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class TextSearchServiceExceptions {
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Response> handleTooManyRequestException(NoSuchElementException ex){
		
		log.error(Constants.EXCEPTION_LOG, ex);
		Response response = Response.builder().errorCode(HttpStatus.NOT_FOUND.value()).errorMessage(HttpStatus.NOT_FOUND.getReasonPhrase()).build();
		return new ResponseEntity<Response>(response, HttpStatus.NOT_FOUND);		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response> handleNotValidException(MethodArgumentNotValidException ex){
		
		log.error(Constants.EXCEPTION_LOG, ex);
		Response response = Response.builder().errorCode(HttpStatus.BAD_REQUEST.value()).errorMessage(ex.getLocalizedMessage()).build();
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> handleException(Exception ex){
		
		log.error(Constants.EXCEPTION_LOG, ex);
		Response response = Response.builder().errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).errorMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).build();
		return new ResponseEntity<Response>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
