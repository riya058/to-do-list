package com.example.myapp.dto;

public class TaskResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String priority;
    private String status;
    private String username;

    public TaskResponseDTO(Long id, String title, String description,
                           String priority, String status, String username) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.username = username;
    }
}
