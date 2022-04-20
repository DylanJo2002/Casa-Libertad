package com.casalibertad.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casalibertad.dtos.request.NewVisitorDTO;
import com.casalibertad.dtos.response.VisitorDTO;
import com.casalibertad.exceptions.ConflictException;
import com.casalibertad.exceptions.NotFoundException;
import com.casalibertad.services.UserService;
import com.casalibertad.services.VisitorService;

@RestController
@RequestMapping("reception/visitor")
public class ReceptionController {
	
	@Autowired
	private VisitorService visitorService;
	
	@GetMapping
	public ResponseEntity<VisitorDTO> getVisitorInformation(@RequestParam int document_type_id
			, @RequestParam String document_number ) throws Exception{

		VisitorDTO visitorDTO = visitorService.getVisitorDTO(document_type_id, document_number);
		return new ResponseEntity<VisitorDTO>(visitorDTO, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<VisitorDTO> createVisitorInformation(@RequestBody NewVisitorDTO newVisitorDTO)
			throws NotFoundException, ConflictException{
		VisitorDTO visitorDTO = null;
		if(visitorService.isValidVisitorDTO(newVisitorDTO)) {
			visitorDTO = visitorService.mapToVisitorDTO(
					visitorService.createVisitorEntity(newVisitorDTO));
		}
		
		return new ResponseEntity<VisitorDTO>(visitorDTO, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> updateVisitorInformation(){
		
		return null;
	}
	
}
