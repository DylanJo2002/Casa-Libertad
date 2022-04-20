package com.casalibertad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.casalibertad.dtos.response.ErrorDTO;
import com.casalibertad.enums.ErrorMessageEnum;
import com.casalibertad.exceptions.NotFoundException;
import com.casalibertad.loggin.ExceptionLoggin;

@RestControllerAdvice
public class ControllerAdvise {

	@Autowired
	private ExceptionLoggin exceptionLoggin;
	
	@ExceptionHandler(Exception.class) 
	public ResponseEntity<ErrorDTO> exceptionHandler(Exception ex) {
		String id = exceptionLoggin.getUUID();
		String message = exceptionLoggin.buildMessage(ErrorMessageEnum.InternalError,id
				,ex.getMessage());
		exceptionLoggin.saveLog(message,id);
		ErrorDTO errorDTO = new ErrorDTO(message);
		return new ResponseEntity<ErrorDTO>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NotFoundException.class) 
	public ResponseEntity<ErrorDTO> exceptionHandler(NotFoundException ex) {
		ErrorDTO errorDTO = new ErrorDTO(ex.getMessage());
		return new ResponseEntity<ErrorDTO>(errorDTO, HttpStatus.NOT_FOUND);
	}
}
