package com.app.lms.Lms.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.app.lms.Lms.model.signUpModel;
import com.app.lms.Lms.model.signUpResponse;
import com.app.lms.Lms.service.signUpServiceImp;

import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class signupController {
	

	@Autowired
	private signUpServiceImp signUpService;

	// Create New User
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public ResponseEntity <signUpResponse>createNewUser(@RequestBody signUpModel signUpBody, HttpServletRequest req) {
	
		return signUpService.createNewUser(signUpBody, req);
	}

}
