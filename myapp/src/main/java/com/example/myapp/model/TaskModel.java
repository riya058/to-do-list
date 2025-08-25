package com.example.myapp.model;
import jakarta.persistence.*;

@Entity
public class TaskModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;  // HIGH, MEDIUM, LOW

    @Enumerated(EnumType.STRING)
    private Status status;     // TODO, IN_PROGRESS, DONE

    // ✅ Always add empty constructor for JPA
    public TaskModel() {
    }

    // ✅ Optional constructor (helps when creating objects in code)
    public TaskModel(String title, String description, Priority priority, Status status) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

    // ✅ Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

// Enums for Priority and Status
enum Priority {
    HIGH, MEDIUM, LOW
}

enum Status {
    TODO, IN_PROGRESS, DONE
}


