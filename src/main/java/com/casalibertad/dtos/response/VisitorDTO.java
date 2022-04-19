package com.casalibertad.dtos.response;

import lombok.Data;

@Data
public class VisitorDTO {
	private int uniqid;
	private UserDTO user;
	private char data_processing_consent;
	private VisitReasonDTO reason_visit;
	private String other_reason;
	private VisitReasonDTO workshop_appointment;
	private ServiceChannelDTO service_channel;
}