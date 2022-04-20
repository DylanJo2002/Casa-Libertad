package com.casalibertad.dtos.request;

import lombok.Data;

@Data
public class NewVisitorDTO {
	private NewUserDTO user;
	private char data_processing_consent;
	private int reason_visit_id;
	private String other_reason;
	private int workshop_appointment_id;
	private int service_channel_id;
}
