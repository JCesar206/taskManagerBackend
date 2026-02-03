package com.julio.taskmanager.controller;

import com.julio.taskmanager.model.User;
import com.julio.taskmanager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final UserService userService;
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public List<User> getAllUsers() {
		log.info("Fetching all users");
		return userService.findAllUsers();
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		log.info("Creating user: {}", user.getEmail());
		User savedUser = userService.saveUser(user);
		return ResponseEntity.ok(savedUser);
	}
}
