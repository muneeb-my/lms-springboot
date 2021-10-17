package com.app.lms.Lms.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity

@Table(name = "login")
@EntityListeners(AuditingEntityListener.class)

public class signUpModel {
	
	
	public UUID getUserID() {
		return userID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID loginId;

	
	public UUID getLoginId() {
		return loginId;
	}

	public void setLoginId(UUID loginId) {
		this.loginId = loginId;
	}
	
	@Column(name = "userid")
	private UUID userID;
	

	public void setUserid(UUID userID) {
		this.userID = userID;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at")
	private Date createdAt;

}
