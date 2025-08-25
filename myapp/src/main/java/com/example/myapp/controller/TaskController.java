package com.example.myapp.controller;
import com.example.myapp.model.TaskModel;
import com.example.myapp.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }
    @GetMapping
    public List<TaskModel> getAllTasks() {
        return taskService.getAllTask();
    }

    @GetMapping("/{id}")
    public TaskModel getTaskByID(@PathVariable Long id) {
        return taskService.getTaskByID(id);
    }

    @PostMapping
    public TaskModel createTask(@RequestBody TaskModel taskModel) {
        return taskService.createTask(taskModel);
    }

    @PutMapping("/{id}")
    public TaskModel updateTask(@PathVariable Long id, @RequestBody TaskModel taskModel) {
        return taskService.updateTask(id, taskModel);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
