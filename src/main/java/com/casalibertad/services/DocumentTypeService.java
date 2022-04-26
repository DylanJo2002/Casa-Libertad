package com.casalibertad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.entities.DocumentTypeEntity;
import com.casalibertad.enums.ErrorMessageEnum;
import com.casalibertad.exceptions.NotFoundException;
import com.casalibertad.loggin.ExceptionLoggin;
import com.casalibertad.repositories.DocumentTypeRepository;

@Service
public class DocumentTypeService {
	
	@Autowired
	private DocumentTypeRepository documentTypeRepository;
	@Autowired
	private ExceptionLoggin exceptionLoggin;
	
	public DocumentTypeEntity getDocumentType(int document_type_id) throws NotFoundException {
		DocumentTypeEntity documentTypeEntity = documentTypeRepository.findByUniqid(document_type_id);
		
		if(documentTypeEntity == null) {
			
			String cause = String.format("Does not exist a document type with id %d", document_type_id);
			String id = exceptionLoggin.getUUID();
			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.NotFoundException, id, cause,
					this.getClass().toString());
			exceptionLoggin.saveLog(message, id);
			
			throw new NotFoundException(message);
		}
		
		return documentTypeEntity;
	}

}
