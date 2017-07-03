package com.niit.collaborationbackend.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(name = "UserProfile")
@Component
public class UserProfile {

	@Id
	@Column
	@NotEmpty(message="Email ID should not be blank")
	@Email(message = "Please enter correct Email ID...")
	private String useremail;
	
	@Column
	@NotEmpty(message="First name should not be left blank")
	private String fstname;
	
	@Column
	@NotEmpty(message="Middle name should not be left blank")
	private String midname;
	
	@Column
	@NotEmpty(message="Last name should not be left blank")
	private String lstname;
	
	@Column
	@NotEmpty(message="Date of birth should not be left blank")
	private String dateofbirth;

	@Column
	@NotEmpty(message="Gender should not be left blank")
	private String gender;

	@Column
	@NotEmpty(message="City/rown should not be left blank")
	private String city;
	
	@Column
	@NotEmpty(message="mobile should not be left blank")
	@Size(min=10,max=10,message = "Mobile number should be 10 digit long")
	private String mobileno;
	
	@Column
	@NotEmpty(message="Password should not be left blank")
	@Size(min = 6, max = 12, message = "Password should be match with between 6 to 15 characters")
	private String password;
	
	@Column
	private String regdate;
	
	@Column
	private String lastmodifiedddate;
	
	@Column
	private String avtar;

	@Column
	@NotNull
	private char approved; //'Y','N' by admin
	
	transient private MultipartFile userFile;

	@Column
	@NotEmpty
	public String currentrole;
	
	@Column
	@NotEmpty
	public String useridentity;

	@Column
	private char useronline;

	@Column
	private String reason;
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCurrentrole() {
		return currentrole;
	}

	public void setCurrentrole(String currentrole) {
		this.currentrole = currentrole;
	}

	public String getUseridentity() {
		return useridentity;
	}

	public void setUseridentity(String useridentity) {
		this.useridentity = useridentity;
	}
	
	/*
	@OneToOne
	@JoinColumn(name="roleid")
	@JsonIgnore
	private UserRole currentrole;
	
	@OneToOne
	@JoinColumn(name="userid")
	@JsonIgnore
	private UserType usertype;
	
	@OneToMany(mappedBy = "userprofile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	public Set<UserBlog> userblog;
	*/
	
	public char getUseronline() {
		return useronline;
	}

	public void setUseronline(char useronline) {
		this.useronline = useronline;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getFstname() {
		return fstname;
	}

	public void setFstname(String fstname) {
		this.fstname = fstname;
	}

	public String getMidname() {
		return midname;
	}

	public void setMidname(String midname) {
		this.midname = midname;
	}

	public String getLstname() {
		return lstname;
	}

	public void setLstname(String lstname) {
		this.lstname = lstname;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getLastmodifiedddate() {
		return lastmodifiedddate;
	}

	public void setLastmodifiedddate(String lastmodifiedddate) {
		this.lastmodifiedddate = lastmodifiedddate;
	}

	public String getAvtar() {
		return avtar;
	}

	public void setAvtar(String avtar) {
		this.avtar = avtar;
	}

	public char getApproved() {
		return approved;
	}

	public void setApproved(char approved) {
		this.approved = approved;
	}

	public MultipartFile getUserFile() {
		return userFile;
	}

	public void setUserFile(MultipartFile userFile) {
		this.userFile = userFile;
	}
}
