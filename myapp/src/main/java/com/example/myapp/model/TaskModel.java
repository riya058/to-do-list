package com.example.myapp.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToOne
    @JoinColumn(name = "user_id") // Hibernate will create user_id column
    @JsonIgnoreProperties
    private UserModel user;

    public TaskModel() {
    }

    public TaskModel(String title, String description, Priority priority, Status status) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

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

    public UserModel getUser() { return user; }
    public void setUser(UserModel user) { this.user = user; }
}


