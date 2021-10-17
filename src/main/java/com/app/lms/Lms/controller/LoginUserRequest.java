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
import com.app.lms.Lms.service.loginUpServiceImp;


import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class LoginUserRequest {
	

	@Autowired
	private loginUpServiceImp loginService;

	// Login User
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ResponseEntity <signUpResponse>loginUser(@RequestBody signUpModel signUpBody, HttpServletRequest req) {
	
		return loginService.loginUser(signUpBody, req);
	}

}
