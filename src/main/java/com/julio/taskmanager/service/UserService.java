package com.julio.taskmanager.service;

import com.julio.taskmanager.model.User;
import com.julio.taskmanager.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import java.util.Optional;

@Service
public class UserService {
	private final UserRepository userRepository;
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public Optional<User> findByEmail(String email) {
		log.info("Searching user by email: {}", email);
		return userRepository.findByEmail(email);
	}
	
	public User saveUser(User user) {
		log.info("Saving user: {}", user.getEmail());
		return userRepository.save(user);
	}
	
	public List<User> findAllUsers() {
		log.info("Fetching all users");
		return userRepository.findAll();
	}
}