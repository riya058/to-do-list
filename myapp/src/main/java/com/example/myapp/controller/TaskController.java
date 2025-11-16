package com.example.myapp.controller;

import com.example.myapp.dto.TaskResponseDTO;
import com.example.myapp.model.TaskModel;
import com.example.myapp.model.UserModel;
import com.example.myapp.security.UserDetailsImpl;
import com.example.myapp.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponseDTO> getAllTasks(@AuthenticationPrincipal UserDetailsImpl currentUser) {
        return taskService.getTasksByUserId(currentUser.getId())
                .stream()
                .map(taskService::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTask(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl currentUser
    ) {
        TaskModel task = taskService.getTaskByID(id);

        if (task == null || !task.getUser().getId().equals(currentUser.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not allowed");
        }

        return ResponseEntity.ok(taskService.convertToDTO(task));
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(
            @RequestBody TaskModel taskModel,
            @AuthenticationPrincipal UserDetailsImpl currentUser
    ) {
        TaskModel createdTask = taskService.createTask(taskModel, currentUser.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.convertToDTO(createdTask));
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(
            @PathVariable Long id,
            @RequestBody TaskModel updatedTask,
            @AuthenticationPrincipal UserDetailsImpl currentUser
    ) {
        TaskModel result = taskService.updateTask(id, updatedTask, currentUser.getId());
        if (result == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not allowed");
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetailsImpl currentUser
    ) {
        boolean ok = taskService.deleteTask(id, currentUser.getId());
        if (!ok) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not allowed");
        return ResponseEntity.ok("Deleted");
    }
}
