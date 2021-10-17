package com.app.lms.Lms.Repository;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.lms.Lms.model.LeaveModel;
import com.app.lms.Lms.model.signUpModel;


public interface LeaveRepository extends JpaRepository<LeaveModel, UUID> {

	LeaveModel findByLeaveID(UUID id);
}
