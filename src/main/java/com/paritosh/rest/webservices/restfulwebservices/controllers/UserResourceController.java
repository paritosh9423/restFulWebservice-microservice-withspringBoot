package com.paritosh.rest.webservices.restfulwebservices.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.paritosh.rest.webservices.restfulwebservices.CustomExceptions.UserNotFoundException;
import com.paritosh.rest.webservices.restfulwebservices.UserDao.UserDAOService;
import com.paritosh.rest.webservices.restfulwebservices.bean.Users;

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

	@GetMapping(path = "/users/{id}")
	public Users getUser(@PathVariable int id) {
		Users userFound = userService.find(id);
		if(userFound==null)
			throw new UserNotFoundException("id-"+id);
		return userFound;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@RequestBody Users user) {
		Users userSaved = userService.save(user);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
		path("/{id}").
		buildAndExpand(userSaved.getUserId()).
		toUri();
		
		return ResponseEntity.created(uri).build();
		

	}

}
