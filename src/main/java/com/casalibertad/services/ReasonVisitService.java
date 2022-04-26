package com.casalibertad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.entities.ReasonVisitEntity;
import com.casalibertad.enums.ErrorMessageEnum;
import com.casalibertad.exceptions.NotFoundException;
import com.casalibertad.loggin.ExceptionLoggin;
import com.casalibertad.repositories.ReasonVisitRepository;

@Service
public class ReasonVisitService {

	@Autowired
	private ReasonVisitRepository reasonVisitRepository;
	@Autowired
	private ExceptionLoggin exceptionLoggin;
	
	public ReasonVisitEntity getReasonVisitEntity(int uniqid) throws NotFoundException {
		ReasonVisitEntity reasonVisitEntity = reasonVisitRepository.findByUniqid(uniqid);
		
		if(reasonVisitEntity == null) {
			String cause = String.format("Does not exist a reason visit with id %d", 
					uniqid);
			String id = exceptionLoggin.getUUID();
			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.NotFoundException, id, cause
					,this.getClass().toString());
			exceptionLoggin.saveLog(message, id);
			
			throw new NotFoundException(message);
		}
		
		return reasonVisitEntity;
	}
	
	public boolean isValidReasonVisitEntity(int uniqid) throws NotFoundException {
		return getReasonVisitEntity(uniqid) != null;
	}
}
