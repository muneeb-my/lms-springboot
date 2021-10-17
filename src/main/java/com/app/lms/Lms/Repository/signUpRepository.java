package com.app.lms.Lms.Repository;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.lms.Lms.model.signUpModel;


public interface signUpRepository extends JpaRepository<signUpModel, UUID> {

	signUpModel findByUsername(String username);

	
	@Transactional 
	@Query("Select a from signUpModel a where username = ?1")
	signUpModel findUser(String username);
	
}
