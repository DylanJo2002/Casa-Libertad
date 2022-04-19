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
@Table(name = "casa_libertad_users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uniqid;
	
	@Column(name = "document_type")
	@ManyToOne
	private DocumentTypeEntity document_type;
	
	@Column(name = "document_number")
	private long document_number;
	
	@Column(name = "names_user")
	private String names_user;
	
	@Column(name = "first_last_name")
	private String first_last_name;
	
	@Column(name = "secound_last_name")
	private String secound_last_name;
	
	@Column(name = "phone_1")
	private String phone_1;
	
	@Column(name = "phone_2")
	private String phone_2;
}
