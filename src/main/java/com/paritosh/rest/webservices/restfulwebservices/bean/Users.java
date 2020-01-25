package com.paritosh.rest.webservices.restfulwebservices.bean;

import java.util.Date;

public class Users {

	private Integer userId ;
	private String userName;
	private Date birthDate;
	
	
	
	public Users() {
		super();
	}

	
	public Users(Integer userId, String userName, Date birthDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.birthDate = birthDate;
	}
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", birthDate=" + birthDate + "]";
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	
}
