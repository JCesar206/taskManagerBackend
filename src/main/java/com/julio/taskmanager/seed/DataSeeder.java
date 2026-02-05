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
			
			Task task1 = new Task(
				"Comprar comida",
				"Ir al supermercado",
				TaskStatus.PENDING,
				TaskCategory.PERSONAL,
				"🛒",
				user
			);
			
			Task task2 = new Task(
				"Estudiar Spring Boot",
				"Repasar controladores y servicios",
				TaskStatus.IN_PROGRESS,
				TaskCategory.SCHOOL,
				"📚",
				user
			);
			
			taskRepository.save(task1);
			taskRepository.save(task2);
			
			log.info("Tasks created");
			log.info("Database seed completed");
		};
	}
}