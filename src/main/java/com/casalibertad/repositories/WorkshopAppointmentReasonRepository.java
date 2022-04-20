package com.casalibertad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casalibertad.entities.WorkshopAppointmentReasonEntity;

@Repository
public interface WorkshopAppointmentReasonRepository extends JpaRepository<WorkshopAppointmentReasonEntity, Integer> {
	public WorkshopAppointmentReasonEntity findByUniqid(int uniqid);
}
