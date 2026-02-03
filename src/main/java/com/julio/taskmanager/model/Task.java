package com.julio.taskmanager.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	private String description;
	
	@Enumerated(EnumType.STRING)
	private TaskStatus status;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	public Task() {
	}
	
	public Task(String title, String description, TaskStatus status, User user) {
		this.title = title;
		this.description = description;
		this.status = status;
		this.user = user;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public TaskStatus getStatus() {
		return status;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}