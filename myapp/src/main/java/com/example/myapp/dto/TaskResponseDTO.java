package com.example.myapp.dto;

public class TaskResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String priority;
    private String status;
    private Long userId;

    public TaskResponseDTO(Long id, String title, String description,
                           String priority, String status, Long userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.userId = userId;
    }

    // Getters only (DTO should not modify data)
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPriority() { return priority; }
    public String getStatus() { return status; }
    public Long getUserId() { return userId; }
}
