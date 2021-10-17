package com.app.lms.Lms.controller;

import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.app.lms.Lms.model.LeaveModel;
import com.app.lms.Lms.service.LeaveServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/v1")
public class LeaveController {

	@Autowired
	private LeaveServiceImp LeaveService;

	// Create New Leave
	@RequestMapping(value = "leave", method = RequestMethod.POST)
	public ResponseEntity<LeaveModel> newLeave(@RequestBody LeaveModel requestBody, HttpServletRequest req) {

		return LeaveService.newLeave(requestBody, req);
	}

	// List All Leaves
	@RequestMapping(value = "leave", method = RequestMethod.GET)
	public ResponseEntity<List<LeaveModel>> getAllLeaves() throws Throwable {
		return LeaveService.getAllLeavesResponse();
	}

	// Delete Leave
	@RequestMapping(value = "leave/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<LeaveModel> deleteLeave(@PathVariable UUID id) {

		System.out.println("API CALLED");
		System.out.println(id);
		return LeaveService.deleteLeavesResponse(id);
	}


	@PutMapping("/leave/{id}")

	public ResponseEntity<LeaveModel> UpdateLeave(@PathVariable(value = "id") UUID id,
			@Validated @RequestBody LeaveModel update)

	{

		return LeaveService.UpdateLeave(id, update);
	}

}
