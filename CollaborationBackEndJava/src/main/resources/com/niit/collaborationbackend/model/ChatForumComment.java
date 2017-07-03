package com.niit.collaborationbackend.model;

import javax.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "ChatForumComment")
@Component
public class ChatForumComment {

	@Id
	@GeneratedValue
	@Column
	private int id;
	
	@Column
	private int cfid;
	
	@Column
	private String useremail;
	
	@Column
	private String message;
	
	@Column
	private String commentdate;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public String getCommentdate() {
		return commentdate;
	}

	public void setCommentdate(String commentdate) {
		this.commentdate = commentdate;
	}
}
