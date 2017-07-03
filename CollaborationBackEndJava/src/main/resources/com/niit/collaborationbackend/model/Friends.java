package com.niit.collaborationbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "Friends")
@Component
public class Friends {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private int reqid;
	
	@Column
	private String requser;
	
	@Column
	private String touser;
	
	@Column
	private char follow; //User can make them as a unfollow
	
	@Column
	private char status;  //Accept, Reject

	@Column
	private char isonline; //if user log into web make it as a 'Y'/'N'
	
	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public char getIsonline() {
		return isonline;
	}

	public void setIsonline(char isonline) {
		this.isonline = isonline;
	}

	public int getReqid() {
		return reqid;
	}

	public void setReqid(int reqid) {
		this.reqid = reqid;
	}

	public String getRequser() {
		return requser;
	}

	public void setRequser(String requser) {
		this.requser = requser;
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public char getFollow() {
		return follow;
	}

	public void setFollow(char follow) {
		this.follow = follow;
	}
}