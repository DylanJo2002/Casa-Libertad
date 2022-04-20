package com.casalibertad.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	
	@ManyToOne
	private UserEntity user_id;
	
	@Column(name = "data_processing_consent")
	private char dataProcessingConsent;
	
	@ManyToOne
	@JoinColumn(name = "reason_visit")
	private ReasonVisitEntity reasonVisit;

	@Column(name = "other_reason")
	private String otherReason;
	
	@ManyToOne
	@JoinColumn(name = "workshop_appointment")
	private WorkshopAppointmentReasonEntity workshopAppointment;
	
	@ManyToOne
	@JoinColumn(name = "service_channel")
	private ServiceChannelEntity serviceChannel;
}
