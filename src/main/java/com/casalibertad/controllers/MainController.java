package com.casalibertad.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("message")
public class MainController {
	
	@GetMapping
	public String getError() throws Exception {
		throw new Exception("Custom error");
	}
	
}
