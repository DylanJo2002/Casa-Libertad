package com.casalibertad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.casalibertad.entities.ReasonVisitEntity;

@Repository
public interface ReasonVisitRepository extends JpaRepository<ReasonVisitEntity, Integer> {
	public ReasonVisitEntity findByUniqid(int uniqid);
}
