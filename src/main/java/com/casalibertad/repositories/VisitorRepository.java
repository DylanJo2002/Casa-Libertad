package com.casalibertad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casalibertad.entities.VisitorEntity;

@Repository
public interface VisitorRepository extends JpaRepository<VisitorEntity, Integer> {
	public VisitorEntity findByUniqid(int uniqid);
}
