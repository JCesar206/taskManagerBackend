package com.julio.taskmanager.service;

import com.julio.taskmanager.model.Task;
import com.julio.taskmanager.model.TaskStatus;
import com.julio.taskmanager.model.User;
import com.julio.taskmanager.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
	
	private final TaskRepository taskRepository;
	private static final Logger log = LoggerFactory.getLogger(TaskService.class);
	
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	// Create / Update (save)
	public Task saveTask(Task task) {
		log.info(
			"Saving task: {} | status: {} | user: {}",
			task.getTitle(),
			task.getStatus(),
			task.getUser().getEmail()
		);
		return taskRepository.save(task);
	}
	
	// READ - tareas por usuario
	public List<Task> getTasksByUser(User user) {
		log.info("Fetching tasks for user: {}", user.getEmail());
		return taskRepository.findByUser(user);
	}
	
	// READ - tarea por ID
	public Optional<Task> findById(Long id) {
		log.info("Fetching task by id: {}", id);
		return taskRepository.findById(id);
	}
	
	// Patch - cambiar estado
	public Task updateStatus(Task task, TaskStatus status) {
		log.info("Updating status of task {} to {}", task.getId(), status);
		task.setStatus(status);
		return taskRepository.save(task);
	}
	
	// Delete
	public void deleteTask(Long taskId) {
		log.info("Deleting task with id: {}", taskId);
		taskRepository.deleteById(taskId);
	}
}