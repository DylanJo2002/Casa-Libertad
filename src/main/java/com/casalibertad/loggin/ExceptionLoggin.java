package com.casalibertad.loggin;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.casalibertad.entities.LogEntity;
import com.casalibertad.enums.ErrorMessageEnum;
import com.casalibertad.services.LogService;

@Component
@Scope("singleton")
public class ExceptionLoggin {
	
	@Autowired
	private LogService logService;
	
	public String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	public String buildMessage(ErrorMessageEnum message, String id, String cause) {
		StringBuilder builder = new StringBuilder(ErrorMessageEnum
				.getKey(message))
				.append(id.concat("."))
				.append(" ".concat(cause));
		return builder.toString();
	}
	
	public void saveLog(String message ,String id) {
		LogEntity logEntity = new LogEntity();
		logEntity.setUniqid(id);
		logEntity.setMessage(message);
		logService.createLog(logEntity);
	}
	
}
