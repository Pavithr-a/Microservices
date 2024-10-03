package com.mins.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mins.rest.webservices.restfulwebservices.UserRepository;
import com.mins.rest.webservices.restfulwebservices.jpa.PostRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import jakarta.validation.Valid;

@RestController

//this is for post 
public class UserJpaResource {	
	
	private PostRepository postRepository;

	private UserRepository userRepository;
	
	
	
	public UserJpaResource(UserRepository userRepository,PostRepository postRepository) {
		this.userRepository=userRepository;
		this.postRepository=postRepository;
	}


	//get users
	@GetMapping("/jpa/users")
	public List<User> retreiveAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		
		Optional<User> user=userRepository.findById(id);
		
		if(user.isEmpty()) {
			throw new UserNotFoundException("id: "+id);
		}
		
		EntityModel<User> entityModel=EntityModel.of(user.get());
		
		WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).retreiveAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
		
	}
	
	

	@PostMapping("/jpa/users")
	public ResponseEntity<User>  createUser(@Valid @RequestBody User user) {
		
		User savedUser=userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
												.path("/{id}")
												.buildAndExpand(savedUser.getId())
												.toUri();
		
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrivePostsForUser(@PathVariable int id) {
		
		Optional<User> user= userRepository.findById(id);
		
		if(user.isEmpty()) {
			throw new UserNotFoundException("id: "+id);
		}	
		
		return user.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object>  createPostForUser(@PathVariable int id,@Valid @RequestBody Post post) {
		
		Optional<User> user= userRepository.findById(id);
		
		if(user.isEmpty()) {
			throw new UserNotFoundException("id: "+id);
		}	
		

		post.setUser(user.get());
		
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
												.path("/{id}")
												.buildAndExpand(savedPost.getId())
												.toUri();
		
		
		return ResponseEntity.created(location).build();
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
