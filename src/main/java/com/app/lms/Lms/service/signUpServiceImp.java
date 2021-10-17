package com.app.lms.Lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.app.lms.Lms.Repository.UserRepository;
import com.app.lms.Lms.Repository.signUpRepository;
import com.app.lms.Lms.exceptions.EmptyPasswordException;
import com.app.lms.Lms.exceptions.UserMissingInformationException;
import com.app.lms.Lms.exceptions.UsernameExistsException;
import com.app.lms.Lms.model.signUpModel;
import com.app.lms.Lms.model.signUpResponse;
import com.app.lms.Lms.model.userModel;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

@Service
public class signUpServiceImp implements SignUpService {

	private signUpRepository signupRepository;

	@Autowired
	private UserRepository userRepository;

	private ApiUtils apiUtils;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public signUpServiceImp(signUpRepository signupRepository, ApiUtils apiUtils) {
		Assert.notNull(signupRepository, "signupRepository must not be null!");
		Assert.notNull(apiUtils, "ApiUtils must not be null!");
		this.signupRepository = signupRepository;
		this.apiUtils = apiUtils;
	}

	@Override
	public ResponseEntity<signUpResponse> createNewUser(signUpModel signUpBody, HttpServletRequest request) {

		if (null == signUpBody.getUsername() || signUpBody.getUsername().length() == 0) {
			throw new UserMissingInformationException();
		}

		else if (null == signUpBody.getPassword() || signUpBody.getPassword().length() == 0) {
			throw new EmptyPasswordException();
		}

		else if (null != signupRepository.findByUsername(signUpBody.getUsername())) {

			throw new UsernameExistsException();

		}

		else {

			UUID userID = UUID.randomUUID();

			signUpBody.setPassword(passwordEncoder.encode(signUpBody.getPassword()));
			signUpBody.setUserid(userID);

			signUpModel newSignUp = signupRepository.saveAndFlush(signUpBody);

			userModel user = new userModel();
			user.setUserId(userID);
			user.setUsername(signUpBody.getUsername());
			user.setUpdatedAt(new Date());
			user.setCreatedAt(new Date());
			user.setUpdatedBy(signUpBody.getUsername());
			userRepository.save(user);

			signUpResponse responseBody = new signUpResponse();
			responseBody.setUserID(newSignUp.getUserID());
			responseBody.setLoginId(newSignUp.getLoginId());
			responseBody.setCreatedAt(newSignUp.getCreatedAt());
			responseBody.setUsername(newSignUp.getUsername());
			responseBody.setCode("SUCCESS");
			responseBody.setMessage("Account Created");
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("Location", contactUrlHelper(newSignUp, request));

			return new ResponseEntity<signUpResponse>(responseBody, responseHeaders, HttpStatus.CREATED);
		}
	}

	/*
	 * Private Methods
	 */

	private String contactUrlHelper(signUpModel signUpBody, HttpServletRequest request) {
		StringBuilder resourcePath = new StringBuilder();

		resourcePath.append(request.getRequestURL());
		resourcePath.append("/");
		resourcePath.append(signUpBody.getUserID());

		return resourcePath.toString();
	}

}