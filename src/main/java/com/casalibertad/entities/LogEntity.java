package com.casalibertad.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Entity
@Table(name = "casa_libertad_log")
@Data
public class LogEntity {
	@Id
	private String uniqid;
	private String message;
	@ColumnDefault("SYSTIMESTAMP")
	private Timestamp error_date;
	
}
