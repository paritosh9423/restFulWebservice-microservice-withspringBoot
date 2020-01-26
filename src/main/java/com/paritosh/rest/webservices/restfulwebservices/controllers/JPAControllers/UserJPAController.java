package com.paritosh.rest.webservices.restfulwebservices.controllers.JPAControllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
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
import com.paritosh.rest.webservices.restfulwebservices.bean.Post;
import com.paritosh.rest.webservices.restfulwebservices.bean.Users;
import com.paritosh.rest.webservices.restfulwebservices.repository.PostRepository;
import com.paritosh.rest.webservices.restfulwebservices.repository.UserRepository;

import io.swagger.annotations.ApiOperation;
@RestController
public class UserJPAController {
	// retrieveAllUsers
	// retrieveUser
	@Autowired
	private UserDAOService userService;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PostRepository postRepo;

	@GetMapping(path = "/jpa/users")
	public List<Users> retrieveAllUsers() {
		//return userService.findAll();
		return userRepo.findAll();
	}

	@GetMapping(path = "/jpa/users/{id}")//
	@ApiOperation(value="get a particular user with user id")
	public Resource<Users> getUser(@PathVariable int id) {
		//Users userFound = userService.find(id);
		Optional<Users> userFound = userRepo.findById(id);
		if(!userFound.isPresent()) {
			throw new UserNotFoundException("id-"+id);
			}
		//HATEOAS
		/*used in spring >2.2.0
		 * 
		 * EntityModel<Users> model = new EntityModel<Users>(userFound);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkTo.withRel("all-users"));*/
		Resource<Users> resource = new Resource<Users>(userFound.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody Users user) {
	
		//Users userSaved = userService.save(user);
		Users userSaved = userRepo.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
		path("/{id}").
		buildAndExpand(userSaved.getUserId()).
		toUri();
		
		return ResponseEntity.created(uri).build();
		

	}
	@DeleteMapping(path="/jpa/users/{id}")
	public void deleteUserbyId(@PathVariable int id){
		/*
		 * boolean status = userService.deletebyId(id); if(!status) throw new
		 * UserNotFoundException("id-"+id);
		 */
		
		userRepo.deleteById(id);
	}
	

	
	@GetMapping(path = "/jpa/users/{id}/post")//
	@ApiOperation(value="get all posts with user id")
	public List<Post> getPostbyUserid(@PathVariable int id) {
		//Users userFound = userService.find(id);
		Optional<Users> userFound = userRepo.findById(id);
		if(!userFound.isPresent()) {
			throw new UserNotFoundException("id-"+id);
			}
		//HATEOAS
		return userFound.get().getPosts();

	}
	
	
	@PostMapping("/jpa/users/{id}/post")
	public ResponseEntity<Object> createPost(@PathVariable int id , @RequestBody Post post) {
	
		//Users userSaved = userService.save(user);
		
		
		Optional<Users> userSaved = userRepo.findById(id);
		if(!userSaved.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}
		post.setUser(userSaved.get());
		postRepo.save(post);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().
		path("/{id}").
		buildAndExpand(userSaved.get().getUserId()).
		toUri();
		
		return ResponseEntity.created(uri).build();
		

	}
	
}
