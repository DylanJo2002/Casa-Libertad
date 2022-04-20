package com.casalibertad.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "casa_libertad_documents")
public class DocumentTypeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uniqid;
	@Column(name = "document_name")
	private String documentName;
}
