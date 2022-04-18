package com.casalibertad.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.casalibertad.dtos.ErrorDTO;
import com.casalibertad.entities.LogEntity;
import com.casalibertad.enums.ErrorMessageEnum;
import com.casalibertad.repositories.LogRepository;
import com.casalibertad.services.LogService;

@RestControllerAdvice
public class ControllerAdvise {
	
	@Autowired
	private LogService logService;
	
	@ExceptionHandler(Exception.class) 
	public ErrorDTO exceptionHandler(Exception ex) {
		String id = getUUID();
		String message = buildMessage(ErrorMessageEnum.InternalError,id
				,ex.getMessage());
		saveLog(message,id);
		ErrorDTO errorDTO = new ErrorDTO(message);
		return errorDTO;
	}
	
	private String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	private String buildMessage(ErrorMessageEnum message, String id, String cause) {
		StringBuilder builder = new StringBuilder(ErrorMessageEnum
				.getKey(message))
				.append(id.concat("."))
				.append(" ".concat(cause));
		return builder.toString();
	}
	
	private void saveLog(String message ,String id) {
		LogEntity logEntity = new LogEntity();
		logEntity.setUniqid(id);
		logEntity.setMessage(message);
		logService.createLog(logEntity);
	}
}
