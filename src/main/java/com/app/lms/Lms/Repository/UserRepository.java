package com.app.lms.Lms.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.lms.Lms.model.userModel;


public interface UserRepository extends JpaRepository<userModel, UUID> {
	
	userModel findByuserId(UUID id);

}
