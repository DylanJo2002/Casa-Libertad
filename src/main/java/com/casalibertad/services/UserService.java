package com.casalibertad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.dtos.response.DocumentTypeDTO;
import com.casalibertad.dtos.response.UserDTO;
import com.casalibertad.entities.DocumentTypeEntity;
import com.casalibertad.entities.UserEntity;
import com.casalibertad.enums.ErrorMessageEnum;
import com.casalibertad.exceptions.NotFoundException;
import com.casalibertad.loggin.ExceptionLoggin;
import com.casalibertad.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private DocumentTypeService documentTypeService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ExceptionLoggin exceptionLoggin;
	
	public UserDTO getUserEntity(int document_type_id, String document_type_number) throws NotFoundException {
		
		//documentTypeService.getDocumentType validate document type existence
		DocumentTypeEntity documentTypeEntity = documentTypeService.getDocumentType(document_type_id);
		UserEntity userEntity = userRepository.findByDocumentTypeAndDocumentNumber(documentTypeEntity, document_type_number);
		
		
		if(userEntity == null) {
			String cause = String.format("Does not exist an user with document type %s and document number %s", 
					documentTypeEntity.getDocumentName(),document_type_number);
			String id = exceptionLoggin.getUUID();
			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.NotFoundException, id, cause);
			exceptionLoggin.saveLog(message, id);
			
			throw new NotFoundException(message);
		}
			
		return mapToUserDTO(userEntity);
	}
	
	private UserDTO mapToUserDTO(UserEntity userEntity) {
		UserDTO userDTO = new UserDTO();
		DocumentTypeDTO documentTypeDTO = new DocumentTypeDTO();
		
		documentTypeDTO.setId(userEntity.getDocumentType().getUniqid());
		documentTypeDTO.setDocument_name(userEntity.getDocumentType().getDocumentName());
		
		userDTO.setUser_id(userEntity.getUniqid());
		userDTO.setDocument_type(documentTypeDTO);
		userDTO.setDocument_number(userEntity.getDocumentNumber());
		userDTO.setNames_user(userEntity.getNamesUser());
		userDTO.setFirst_last_name(userEntity.getFirstLastName());
		userDTO.setSecound_last_name(userEntity.getSecoundLastName());
		userDTO.setPhone_1(userEntity.getPhone1());
		userDTO.setPhone_2(userEntity.getPhone2());
		
		return userDTO; 
	}
}
