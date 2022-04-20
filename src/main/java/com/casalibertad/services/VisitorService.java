package com.casalibertad.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casalibertad.dtos.response.VisitorDTO;

@Service
public class VisitorService {
	
	@Autowired
	private UserService userService;


	public VisitorDTO getVisitorInformation(int document_type_id, String document_type_number ) {
		
		
		
		return null;
	}
}
