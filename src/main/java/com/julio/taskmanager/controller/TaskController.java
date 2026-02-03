package com.julio.taskmanager.controller;

import com.julio.taskmanager.model.Task;
import com.julio.taskmanager.model.TaskStatus;
import com.julio.taskmanager.model.User;
import com.julio.taskmanager.service.TaskService;
import com.julio.taskmanager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	private final TaskService taskService;
	private final UserService userService;
	private static final Logger log = LoggerFactory.getLogger(TaskController.class);
	
	public TaskController(TaskService taskService, UserService userService) {
		this.taskService = taskService;
		this.userService = userService;
	}
	
	// GET - tareas por usuario
	@GetMapping("/user/{email}")
	public ResponseEntity<List<Task>> getTasksByUser(@PathVariable String email) {
		log.info("Fetching tasks for user: {}", email);
		Optional<User> user = userService.findByEmail(email);
		
		return user
			.map(value -> ResponseEntity.ok(taskService.getTasksByUser(value)))
			.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	// POST - crear tarea
	@PostMapping("/user/{email}")
	public ResponseEntity<Task> createTask(
		@PathVariable String email,
		@RequestBody Task task
	) {
		log.info("Creating task '{}' for user: {}", task.getTitle(), email);
		Optional<User> user = userService.findByEmail(email);
		
		if (user.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		task.setUser(user.get());
		Task savedTask = taskService.saveTask(task);
		return ResponseEntity.ok(savedTask);
	}
	
	// Put - editar tarea
	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(
		@PathVariable Long id,
		@RequestBody Task updatedTask
	) {
		log.info("Updating task id: {}", id);
		
		Optional<Task> taskOpt = taskService.findById(id);
		if (taskOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Task task = taskOpt.get();
		task.setTitle(updatedTask.getTitle());
		task.setDescription(updatedTask.getDescription());
		task.setStatus(updatedTask.getStatus());
		
		return ResponseEntity.ok(taskService.saveTask(task));
	}
	
	// Patch - cambiar estado
	@PatchMapping("/{id}/status")
	public ResponseEntity<Task> updateTaskStatus(
		@PathVariable Long id,
		@RequestParam TaskStatus status
	) {
		log.info("Updating status of task {} to {}", id, status);
		
		Optional<Task> taskOpt = taskService.findById(id);
		if (taskOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Task task = taskOpt.get();
		task.setStatus(status);
		
		return ResponseEntity.ok(taskService.saveTask(task));
	}
	
	// Delete
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
		log.info("Deleting task with id: {}", id);
		taskService.deleteTask(id);
		return ResponseEntity.ok().build();
	}
}
