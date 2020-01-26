package com.paritosh.rest.webservices.restfulwebservices.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;

import com.paritosh.rest.webservices.restfulwebservices.bean.HelloWorldBean;

//Controller
@RestController
public class HelloWorldController {

	
	//I18N
	@Autowired
	private  MessageSource messageSource;
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
	  
	  /*@GetMapping(path = "/hello-world-i18n")
		public String helloWorldInternatioanlized(@RequestHeader(name="Accept-Language",required=false) Locale locale) {
			return messageSource.getMessage("good.morning.message", null ,locale);
		//ITS NOT PRACTICAL TO WRITE REQUESTHEADER FOR EACH METHOD , SO SPRING PROVIDES ANOTHER WAY by using LocalContextHolder AS WRITTEN BELOW
		}*/
	  
	  @GetMapping(path = "/hello-world-i18n")
		public String helloWorldInternatioanlized() {
		  
			return messageSource.getMessage("good.morning.message", null ,LocaleContextHolder.getLocale());
		}

}
