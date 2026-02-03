package com.julio.taskmanager.repository;

import com.julio.taskmanager.model.Task;
import com.julio.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
	List<Task> findByUser(User user);
}