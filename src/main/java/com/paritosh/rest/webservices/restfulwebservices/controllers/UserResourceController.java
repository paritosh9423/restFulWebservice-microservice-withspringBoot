package com.paritosh.rest.webservices.restfulwebservices.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.paritosh.rest.webservices.restfulwebservices.CustomExceptions.UserNotFoundException;
import com.paritosh.rest.webservices.restfulwebservices.UserDao.UserDAOService;
import com.paritosh.rest.webservices.restfulwebservices.bean.Users;

import io.swagger.annotations.ApiOperation;

/*
 * used in spring boot >=2.2.0
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;*/
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;

@RestController
public class UserResourceController {
	// retrieveAllUsers
	// retrieveUser
	@Autowired
	private UserDAOService userService;

	@GetMapping(path = "/users")
	public List<Users> retrieveAllUsers() {
		return userService.findAll();
	}

	@GetMapping(path = "/users/{id}")//
	@ApiOperation(value="get a particular user with user id")
	public Resource<Users> getUser(@PathVariable int id) {
		Users userFound = userService.find(id);
		if(userFound==null)
			throw new UserNotFoundException("id-"+id);
		//HATEOAS
		/*used in spring >2.2.0
		 * 
		 * EntityModel<Users> model = new EntityModel<Users>(userFound);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkTo.withRel("all-users"));*/
		Resource<Users> resource = new Resource<Users>(userFound);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody Users user) {
		Users userSaved = userService.save(user);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
		path("/{id}").
		buildAndExpand(userSaved.getUserId()).
		toUri();
		
		return ResponseEntity.created(uri).build();
		

	}
	@DeleteMapping(path="/users/{id}")
	public void deleteUserbyId(@PathVariable int id){
		boolean status = userService.deletebyId(id);
		if(!status)
			throw new UserNotFoundException("id-"+id);
	}
	
	
	
	
}
