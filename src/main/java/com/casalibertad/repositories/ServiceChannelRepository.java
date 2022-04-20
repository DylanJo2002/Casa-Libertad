package com.casalibertad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.casalibertad.entities.ServiceChannelEntity;

@Repository
public interface ServiceChannelRepository extends JpaRepository<ServiceChannelEntity, Integer>{
	public ServiceChannelEntity findByUniqid(int uniqid);
}
