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
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity

@Table(name = "leave")
@EntityListeners(AuditingEntityListener.class)

public class LeaveModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID leaveID;

	@Column(name = "user_id", nullable = false)
	private String userID;

	@Column(name = "from_date", nullable = false)
	private String fromDate;

	@Column(name = "to_date", nullable = false)
	private String toDate;

	@Column(name = "type_of_leave", nullable = false)
	private String typeOfLeave;

	@Column(name = "reason_of_leave", nullable = false)
	private String reasonOfLeave;

	public UUID getLeaveID() {
		return leaveID;
	}

	public void setLeaveID(UUID leaveID) {
		this.leaveID = leaveID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getTypeOfLeave() {
		return typeOfLeave;
	}

	public void setTypeOfLeave(String typeOfLeave) {
		this.typeOfLeave = typeOfLeave;
	}

	public String getReasonOfLeave() {
		return reasonOfLeave;
	}

	public void setReasonOfLeave(String reasonOfLeave) {
		this.reasonOfLeave = reasonOfLeave;
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

	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	String code;




}
