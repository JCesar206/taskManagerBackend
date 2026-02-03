package com.julio.taskmanager.seed;

import com.julio.taskmanager.model.*;
import com.julio.taskmanager.repository.TaskRepository;
import com.julio.taskmanager.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {
	
	private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);
	
	@Bean
	CommandLineRunner initData(UserRepository userRepository, TaskRepository taskRepository) {
		return args -> {
			
			if (userRepository.count() > 0) {
				log.info("Seed skipped: users already exist");
				return;
			}
			
			log.info("Starting database seed...");
			
			User admin = new User(
				"admin@mail.com",
				"admin123",
				Role.ADMIN
			);
			
			User user = new User(
				"user@mail.com",
				"user123",
				Role.USER
			);
			
			userRepository.save(admin);
			userRepository.save(user);
			
			log.info("Users created");
			
			Task t1 = new Task(
				"Learn Spring Boot",
				"Start with controllers and services",
				TaskStatus.PENDING,
				admin
			);
			
			Task t2 = new Task(
				"Build Task Manager",
				"CRUD + Auth + JWT",
				TaskStatus.IN_PROGRESS,
				user
			);
			
			taskRepository.save(t1);
			taskRepository.save(t2);
			
			log.info("Tasks created");
			log.info("Database seed completed");
		};
	}
}
