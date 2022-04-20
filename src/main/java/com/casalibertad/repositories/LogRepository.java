package com.casalibertad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casalibertad.entities.LogEntity;

@Repository
public interface LogRepository extends JpaRepository<LogEntity, String>{
	

}
