package com.app.lms.Lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.app.lms.Lms.Repository.LeaveRepository;
import com.app.lms.Lms.Repository.UserRepository;
import com.app.lms.Lms.Repository.signUpRepository;
import com.app.lms.Lms.exceptions.EmptyPasswordException;
import com.app.lms.Lms.exceptions.LeaveNotFoundException;
import com.app.lms.Lms.exceptions.UserMissingInformationException;
import com.app.lms.Lms.exceptions.UsernameExistsException;
import com.app.lms.Lms.model.LeaveModel;
import com.app.lms.Lms.model.signUpModel;
import com.app.lms.Lms.model.signUpResponse;
import com.app.lms.Lms.model.userModel;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImp implements UserService {

	private UserRepository userRepository;
	private ApiUtils apiUtils;

	@Autowired
	public UserServiceImp(UserRepository userRepository, ApiUtils apiUtils) {
		Assert.notNull(userRepository, "userRepository must not be null!");
		Assert.notNull(apiUtils, "ApiUtils must not be null!");
		this.userRepository = userRepository;
		this.apiUtils = apiUtils;
	}

	@Override
	public ResponseEntity<userModel> getUserById(UUID id) {
		userModel existing = findIfExists(id);
		return new ResponseEntity<userModel>(existing, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<userModel> UpdateUser(UUID id, userModel update) {
		userModel existing = findIfExists(id);
		existing.setUserAddress(update.getUserAddress());
		existing.setUserDateOfBirth(update.getUserDateOfBirth());
		existing.setUpdatedAt(new Date());
		existing.setCode("SUCCESS");
		existing.setMessage("User Updated");
		userRepository.save(existing);
		return new ResponseEntity<userModel>(existing, HttpStatus.OK);
	}

	/*
	 * Private Methods
	 */

	private userModel findIfExists(UUID id) {
		userModel existing = userRepository.findByuserId(id);

		if (null != existing) {
			return existing;
		} else {
			throw new LeaveNotFoundException();
		}
	}

	private String contactUrlHelper(LeaveModel leave, HttpServletRequest request) {
		StringBuilder resourcePath = new StringBuilder();

		resourcePath.append(request.getRequestURL());
		resourcePath.append("/");
		resourcePath.append(leave.getLeaveID());

		return resourcePath.toString();
	}

}