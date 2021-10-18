package com.app.lms.Lms.service;

import org.springframework.http.ResponseEntity;

import com.app.lms.Lms.model.LeaveModel;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public interface LeaveService {

	public ResponseEntity<LeaveModel> newLeave(LeaveModel RequestBody, HttpServletRequest request);

    public ResponseEntity<List<LeaveModel>> getAllLeavesResponse(String userID);
    
    public ResponseEntity<LeaveModel> deleteLeavesResponse(UUID id);
    
    public ResponseEntity<LeaveModel> UpdateLeave(UUID id, LeaveModel update);


}