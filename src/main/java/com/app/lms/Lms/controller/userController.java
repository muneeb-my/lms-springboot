package com.app.lms.Lms.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.lms.Lms.model.userModel;
import com.app.lms.Lms.service.UserServiceImp;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class userController {

	@Autowired
	private UserServiceImp userService;

	// get user by ID
	@RequestMapping(value = "user/{id}", method = RequestMethod.GET)
	public ResponseEntity<userModel> getUserData(@PathVariable UUID id, HttpServletRequest req) {

		return userService.getUserById(id);
	}

	@PutMapping("/user/{id}")

	public ResponseEntity<userModel> UpdateUser(@PathVariable(value = "id") UUID id,
			@Validated @RequestBody userModel update)

	{

		return userService.UpdateUser(id, update);
	}
}
