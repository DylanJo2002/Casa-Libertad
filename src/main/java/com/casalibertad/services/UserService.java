package com.casalibertad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.dtos.request.NewUserDTO;
import com.casalibertad.dtos.response.DocumentTypeDTO;
import com.casalibertad.dtos.response.UserDTO;
import com.casalibertad.entities.DocumentTypeEntity;
import com.casalibertad.entities.UserEntity;
import com.casalibertad.enums.ErrorMessageEnum;
import com.casalibertad.exceptions.ConflictException;
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
	
	public UserEntity getUserEntity(int document_type_id, String document_type_number) throws NotFoundException {
		
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
			
		return userEntity;
	}
	
	public UserDTO getUserDTO(int document_type_id, String document_type_number) throws NotFoundException {
		return mapToUserDTO(getUserEntity(document_type_id, document_type_number));
	}
	
	public UserDTO mapToUserDTO(UserEntity userEntity) {
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

	public UserEntity createUserEntity(NewUserDTO userDTO) throws NotFoundException {
		UserEntity userEntity = new UserEntity();
		DocumentTypeEntity documentTypeEntity = documentTypeService.getDocumentType(
				userDTO.getDocument_type_id());
		
		userEntity.setDocumentType(documentTypeEntity);
		userEntity.setDocumentNumber(userDTO.getDocument_number());
		userEntity.setNamesUser(userDTO.getNames_user());
		userEntity.setFirstLastName(userDTO.getFirst_last_name());
		userEntity.setSecoundLastName(userDTO.getSecound_last_name());
		userEntity.setPhone1(userDTO.getPhone_1());
		userEntity.setPhone2(userDTO.getPhone_2());
		
		return userRepository.save(userEntity);
	}
	
	public UserEntity updateUserEntity(int document_type_id, String document_number,
			NewUserDTO userDTO) throws NotFoundException {
		
		DocumentTypeEntity documentTypeEntity = documentTypeService.getDocumentType(document_type_id);
		DocumentTypeEntity updatedocumentTypeEntity = documentTypeService.getDocumentType(
				userDTO.getDocument_type_id());
		
		UserEntity userEntity = userRepository.findByDocumentTypeAndDocumentNumber(
				documentTypeEntity, document_number);
		
		
		userEntity.setDocumentType(updatedocumentTypeEntity);
		userEntity.setDocumentNumber(userDTO.getDocument_number());
		userEntity.setNamesUser(userDTO.getNames_user());
		userEntity.setFirstLastName(userDTO.getFirst_last_name());
		userEntity.setSecoundLastName(userDTO.getSecound_last_name());
		userEntity.setPhone1(userDTO.getPhone_1());
		userEntity.setPhone2(userDTO.getPhone_2());
		
		return userRepository.save(userEntity);
	}

	public boolean isValidNewUserDTO(NewUserDTO userDTO) 
			throws NotFoundException, ConflictException {
		
		
		DocumentTypeEntity documentTypeEntity = documentTypeService.getDocumentType(
				userDTO.getDocument_type_id());
		
		UserEntity userEntity = userRepository
				.findByDocumentTypeAndDocumentNumber(documentTypeEntity, userDTO.getDocument_number());

		/*To valid there is not some user with same document type and number */

		if(userEntity != null) {
			String cause = String.format("There is already an user registred with %s and %s", 
					documentTypeEntity.getDocumentName(),userDTO.getDocument_number());
			String id = exceptionLoggin.getUUID();
			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.ConflictException, id, cause);
			exceptionLoggin.saveLog(message, id);
			
			throw new ConflictException(message);
		}
		

		return documentTypeEntity != null;
	}
	
	public boolean isValidUpdatedUserDTO(int document_type_id, String document_number, NewUserDTO userDTO) 
			throws NotFoundException, ConflictException {
		
		DocumentTypeEntity documentTypeEntity =  documentTypeService.getDocumentType(document_type_id);
		UserEntity userEntity = userRepository
				.findByDocumentTypeAndDocumentNumber(documentTypeEntity, document_number);
		
		/*To valid there is the user to update */
		if(userEntity == null) {
			String cause = String.format("There is not an user registred with %s and %s", 
					documentTypeEntity.getDocumentName(),userDTO.getDocument_number());
			String id = exceptionLoggin.getUUID();
			String message = exceptionLoggin.buildMessage(ErrorMessageEnum.NotFoundException, id, cause);
			exceptionLoggin.saveLog(message, id);
			
			throw new NotFoundException(message);
		}
		
		if(userEntity.getDocumentNumber() != userDTO.getDocument_number() 
				&& userEntity.getDocumentType().getUniqid() != userDTO.getDocument_type_id()) {
			/*To valid there is not other user with same document type and document number update */
			return isValidNewUserDTO(userDTO);
		}

		return true;
	}

}
