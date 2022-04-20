package com.casalibertad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casalibertad.entities.UserEntity;
import com.casalibertad.entities.VisitorEntity;

@Repository
public interface VisitorRepository extends JpaRepository<VisitorEntity, Integer> {
	public VisitorEntity findFirstByUserOrderByCreatedDateDesc(UserEntity user);
}
