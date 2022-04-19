package com.casalibertad.dtos.response;

import lombok.Data;

@Data
public class UserDTO {
	private int user_id;
	private DocumentTypeDTO document_type;
	private long document_number;
	private String names_user;
	private String first_last_name;
	private String secound_last_name;
	private long phone_1;
	private long phone_2;
}
