package com.casalibertad.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reception/visitor")
public class ReceptionController {
	
	@GetMapping
	public ResponseEntity<?> getVisitorInformation(@RequestParam long document_type_id
			, @RequestParam String document_type_number ){
		
		return null;
	}
	
	@PostMapping
	public ResponseEntity<?> createVisitorInformation(){
		
		return null;
	}
	
	@PutMapping
	public ResponseEntity<?> updateVisitorInformation(){
		
		return null;
	}
	
}
