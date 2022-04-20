package com.casalibertad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.entities.WorkshopAppointmentReasonEntity;
import com.casalibertad.enums.ErrorMessageEnum;
import com.casalibertad.exceptions.NotFoundException;
import com.casalibertad.loggin.ExceptionLoggin;
import com.casalibertad.repositories.WorkshopAppointmentReasonRepository;

@Service
public class WorkshopAppointmentService {

	@Autowired
	private WorkshopAppointmentReasonRepository appointmentReasonRepository;
	@Autowired
	private ExceptionLoggin exceptionLoggin;
	
	public WorkshopAppointmentReasonEntity getWorkshopAppointment(int uniqid) throws NotFoundException {
		WorkshopAppointmentReasonEntity appointmentReasonEntity 
			= appointmentReasonRepository.findByUniqid(uniqid);
		
		if(appointmentReasonEntity == null) {
			String cause = String.format("Does not exist a worskhop appointment with id %d", 
					uniqid);
			String id = exceptionLoggin.getUUID();
			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.NotFoundException, id, cause);
			exceptionLoggin.saveLog(message, id);
			
			throw new NotFoundException(message);
		}
		
		return appointmentReasonEntity;
	}
	
	public boolean isValidWorkshopAppointment(int uniqid) throws NotFoundException {
		return getWorkshopAppointment(uniqid) != null;
	}
}
