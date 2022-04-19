package com.casalibertad.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "casa_libertad_visitors")
public class VisitorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uniqid;
	
	@Column(name = "user_id")
	@ManyToOne
	private UserEntity user_id;
	
	@Column(name = "data_processing_consent")
	private char data_processing_consent;
	
	@Column(name = "reason_visit")
	@ManyToOne
	private ReasonVisitEntity reason_visit;

	@Column(name = "other_reason")
	private String other_reason;
	
	@Column(name = "workshop_appointment")
	@ManyToOne
	private WorkshopAppointmentReasonEntity workshop_appointment;
	
	@Column(name = "service_channel")
	@ManyToOne
	private ServiceChannelEntity service_channel;
}
