package com.paritosh.rest.webservices.restfulwebservices.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.paritosh.rest.webservices.restfulwebservices.bean.HelloWorldBean;

//Controller
@RestController
public class HelloWorldController {

	// method -- return hello World
	// GET METHOD
	// @RequestMapping(method=RequestMethod.GET, path="/hello-world-test")
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	
	  @GetMapping(path="/hello-world-bean") 
	  public HelloWorldBean helloWorldBean(){ 
		  return new HelloWorldBean("Hello World"); 
		  }
	 
	  @GetMapping(path="/hello-world-bean/path-variable/{message}") 
	  public HelloWorldBean helloWorldBeanUserMessage(@PathVariable String message){ 
		  return new HelloWorldBean(message); 
		  }

}
