package com.app.lms.Lms.service;

import org.springframework.http.ResponseEntity;

import com.app.lms.Lms.model.userModel;

import java.util.UUID;

public interface UserService {

	public ResponseEntity<userModel> getUserById(UUID id);

	public ResponseEntity<userModel> UpdateUser(UUID id, userModel update);

}