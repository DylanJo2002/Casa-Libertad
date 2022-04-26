package com.casalibertad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.entities.ServiceChannelEntity;
import com.casalibertad.enums.ErrorMessageEnum;
import com.casalibertad.exceptions.NotFoundException;
import com.casalibertad.loggin.ExceptionLoggin;
import com.casalibertad.repositories.ServiceChannelRepository;

@Service
public class ServiceChannelService {

	@Autowired
	private ServiceChannelRepository serviceChannelRepository;
	@Autowired
	private ExceptionLoggin exceptionLoggin;
	
	public ServiceChannelEntity getServiceChannelEntity(int uniqid) throws NotFoundException {
		ServiceChannelEntity serviceChannelEntity = 
				serviceChannelRepository.findByUniqid(uniqid);
		
		if(serviceChannelEntity == null) {
			String cause = String.format("Does not exist a service channel with id %d", 
					uniqid);
			String id = exceptionLoggin.getUUID();
			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.NotFoundException, id, cause
					,this.getClass().toString());
			exceptionLoggin.saveLog(message, id);
			
			throw new NotFoundException(message);
		}
		
		return serviceChannelEntity;
	}
	
	public boolean isValidServiceChannelEntity(int uniqid) throws NotFoundException {
		return getServiceChannelEntity(uniqid) != null;
	}
}
