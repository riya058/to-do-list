package com.example.myapp.service;

import com.example.myapp.model.TaskModel;
import com.example.myapp.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService  {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
    public List<TaskModel> getAllTask(){
        return taskRepository.findAll();
    }
    public TaskModel getTaskByID(Long id){
        return taskRepository.findById(id).orElse(null);
    }
    public TaskModel createTask(TaskModel taskModel){
        return taskRepository.save(taskModel);
    }
    public TaskModel updateTask(Long id, TaskModel updatedTask) {
        return taskRepository.findById(id).map(task -> {
                    task.setTitle(updatedTask.getTitle());
                    task.setDescription(updatedTask.getDescription());
                    task.setPriority(updatedTask.getPriority());
                    task.setStatus(updatedTask.getStatus());
                    return taskRepository.save(task);
                })
                .orElse(null);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
