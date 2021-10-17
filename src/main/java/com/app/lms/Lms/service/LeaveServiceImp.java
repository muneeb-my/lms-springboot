package com.app.lms.Lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.app.lms.Lms.Repository.LeaveRepository;
import com.app.lms.Lms.Repository.signUpRepository;
import com.app.lms.Lms.exceptions.EmptyPasswordException;
import com.app.lms.Lms.exceptions.LeaveNotFoundException;
import com.app.lms.Lms.exceptions.UserMissingInformationException;
import com.app.lms.Lms.exceptions.UsernameExistsException;
import com.app.lms.Lms.model.LeaveModel;
import com.app.lms.Lms.model.signUpModel;
import com.app.lms.Lms.model.signUpResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

@Service
public class LeaveServiceImp implements LeaveService {

	private LeaveRepository leaveRepository;

	private ApiUtils apiUtils;

	@Autowired
	public LeaveServiceImp(LeaveRepository leaveRepository, ApiUtils apiUtils) {
		Assert.notNull(leaveRepository, "leaveRepository must not be null!");
		Assert.notNull(apiUtils, "ApiUtils must not be null!");
		this.leaveRepository = leaveRepository;
		this.apiUtils = apiUtils;
	}

	@Override
	public ResponseEntity<LeaveModel> newLeave(LeaveModel RequestBody, HttpServletRequest request) {

		LeaveModel newLeave = leaveRepository.saveAndFlush(RequestBody);
		newLeave.setCode("SUCCESS");
		newLeave.setMessage("Leave Added");
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Location", contactUrlHelper(newLeave, request));

		return new ResponseEntity<LeaveModel>(newLeave, responseHeaders, HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<List<LeaveModel>> getAllLeavesResponse() {

		List<LeaveModel> allLeaves = leaveRepository.findAll();

		return new ResponseEntity<List<LeaveModel>>(allLeaves, HttpStatus.OK);

	}
	
    @Override
    public ResponseEntity<LeaveModel> deleteLeavesResponse(UUID id) {
    	LeaveModel existing = findIfExists(id);
    	leaveRepository.deleteById(id);
        return new ResponseEntity<LeaveModel>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<LeaveModel> UpdateLeave(UUID id, LeaveModel update) {
    	LeaveModel existing = findIfExists(id);
    	existing.setFromDate(update.getFromDate());
    	existing.setReasonOfLeave(update.getReasonOfLeave());
    	existing.setToDate(update.getToDate());
    	existing.setTypeOfLeave(update.getTypeOfLeave());
    	existing.setCode("SUCCESS");
    	existing.setMessage("Leave Updated");
    	leaveRepository.save(existing);
        return new ResponseEntity<LeaveModel>(existing,HttpStatus.OK);
    }
    

	/*
	 * Private Methods
	 */

	
    private LeaveModel findIfExists(UUID id) {
    	LeaveModel existing =  leaveRepository.findByLeaveID(id);

        if(null != existing) {
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