package com.app.lms.Lms.service;

import org.springframework.http.ResponseEntity;
import com.app.lms.Lms.model.signUpModel;
import com.app.lms.Lms.model.signUpResponse;

import javax.servlet.http.HttpServletRequest;

public interface SignUpService {

	public ResponseEntity<signUpResponse> createNewUser(signUpModel signUpBody, HttpServletRequest request);

}