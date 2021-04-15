package com.security.mssecurity.user;

import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	UserService service;
	
	
	/**
	 * 
	 * @return Message index
	 */
	@GetMapping("/index")
	public String index() {
		return "Controller User Running...";
	}
	
			
	/**
	 * 
	 * @return List Users
	 */
	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() throws ExecutionException, InterruptedException{		
		return ResponseEntity.ok().body(service.findAll());
	}
	
	
	/**
	 * 
	 * @param email
	 * @return User for email
	 */
	@GetMapping("/users/{email}")
	public ResponseEntity<?> getByEmailUsers(@PathVariable String email) throws ExecutionException, InterruptedException {
		return ResponseEntity.ok().body(service.findByEmail(email));
	}
	
	/**
	 * 
	 * @param name
	 * @return User for name
	 */
	@GetMapping("/users/name/{name}")
	public ResponseEntity<?> getByNameUsers(@PathVariable String name) throws ExecutionException, InterruptedException {
		return ResponseEntity.ok().body(service.findByName(name));
	}
	
	/**
	 * 
	 * @return Message Access Denied
	 */
	@GetMapping(value = "/access-denied")
	public ResponseEntity<String> accessDenied() {
		String mensage = "Access Denied!!!";
		return ResponseEntity.ok().body(mensage);
	}
	
	/**
	 * 
	 * @param User
	 * @return Status 201
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@PostMapping("/users")
	public ResponseEntity<?> save(@RequestBody User user) throws ExecutionException, InterruptedException {
		service.save(user);
		return ResponseEntity.ok().build();
	}
	
	
	/**
	 * 
	 * @param ID and User
	 * @param User
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	@PutMapping("/users/{id}")
    public void update(@PathVariable String id,  @RequestBody User obj) throws ExecutionException, InterruptedException {
		service.update(id, obj);
	}
	
	
	/**
	 * 
	 * @param ID
	 */
	@DeleteMapping("/users/{id}")
    public void delete(@PathVariable String id){
		service.delete(id);
	}
	
}
