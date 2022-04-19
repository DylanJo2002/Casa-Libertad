package com.casalibertad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casalibertad.entities.DocumentTypeEntity;
import com.casalibertad.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	
	public UserEntity findByDocumentTypeAndDocumentNumber(DocumentTypeEntity documentTypeEntity, long documentNumber);

}
