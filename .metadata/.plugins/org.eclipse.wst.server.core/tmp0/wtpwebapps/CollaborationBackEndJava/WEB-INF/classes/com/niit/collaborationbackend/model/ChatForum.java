package com.niit.collaborationbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "ChatForum")
@Component
public class ChatForum {

	@Id
	@Column
	@GeneratedValue
	private int cfid;
	
	@Column
	private String useremail;
	
	@Column
	private String message;
	
	@Column
	private String createddate;

	public int getCfid() {
		return cfid;
	}

	public void setCfid(int cfid) {
		this.cfid = cfid;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCreateddate() {
		return createddate;
	}

	public void setCreateddate(String createddate) {
		this.createddate = createddate;
	}
	
}
