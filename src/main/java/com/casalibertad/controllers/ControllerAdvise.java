package com.casalibertad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
	public ErrorDTO exceptionHandler(Exception ex) {
		String id = exceptionLoggin.getUUID();
		String message = exceptionLoggin.buildMessage(ErrorMessageEnum.InternalError,id
				,ex.getMessage());
		exceptionLoggin.saveLog(message,id);
		ErrorDTO errorDTO = new ErrorDTO(message);
		return errorDTO;
	}
	
	@ExceptionHandler(NotFoundException.class) 
	public ErrorDTO exceptionHandler(NotFoundException ex) {
		ErrorDTO errorDTO = new ErrorDTO(ex.getMessage());
		return errorDTO;
	}
}
