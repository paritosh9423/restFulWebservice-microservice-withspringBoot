package com.paritosh.rest.webservices.restfulwebservices.bean;

public class HelloWorldBean {
 private String message;
 private String constant = "CUSTOM";
 public HelloWorldBean(String message){
	 this.message = message;
 }

public String getMessage() {
	return message;
}

public String getConstant() {
	return constant;
}

public void setMessage(String message) {
	this.message = message;
}
 
}
