package com.julio.taskmanager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.julio.taskmanager.model.TaskCategory;

@Entity
@Table(name = "tasks")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TaskStatus status = TaskStatus.PENDING;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TaskCategory category = TaskCategory.PERSONAL;
	
	@Column(length = 5)
	private String emoji;
	
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	public Task() {
	}
	
	public Task(String title, String description, TaskStatus status, TaskCategory category, String emoji, User user) {
		this.title = title;
		this.description = description;
		this.status = status;
		this.category = category;
		this.emoji = emoji;
		this.user = user;
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
	}
	
	// ===== GETTERS =====
	
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
	
	public TaskCategory getCategory() {
		return category;
	}
	
	public String getEmoji() {
		return emoji;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public User getUser() {
		return user;
	}
	
	// ===== SETTERS =====
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	
	public void setCategory(TaskCategory category) {
		this.category = category;
	}
	
	public void setEmoji(String emoji) {
		this.emoji = emoji;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}