package com.casalibertad.dtos.request;

import lombok.Data;

@Data
public class NewUserDTO {
	private int document_type_id;
	private String document_number;
	private String names_user;
	private String first_last_name;
	private String secound_last_name;
	private Long phone_1;
	private Long phone_2;
}
