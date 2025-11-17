package com.example.myapp.service;

import com.example.myapp.dto.TaskResponseDTO;
import com.example.myapp.model.TaskModel;
import com.example.myapp.model.UserModel;
import com.example.myapp.repository.TaskRepository;
import com.example.myapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService  {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository){
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<TaskModel> getTasksByUserId(String username) {
        return taskRepository.findByUsername(username);
    }

    public TaskModel getTaskByID(Long id){
        return taskRepository.findById(id).orElse(null);
    }

    public TaskModel createTask(TaskModel taskModel, Long userId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        taskModel.setUsername(user.username);
        return taskRepository.save(taskModel);
    }



    public TaskModel updateTask(Long id, TaskModel updatedTask, String username) {
        return taskRepository.findById(id).map(task -> {
            if (!task.getUsername().equals(username)) return null;

            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setPriority(updatedTask.getPriority());
            task.setStatus(updatedTask.getStatus());

            return taskRepository.save(task);
        }).orElse(null);
    }

    public boolean deleteTask(Long id, String username) {
        return taskRepository.findById(id).map(task -> {
            if (!task.getUsername().equals(username)) return false;
            taskRepository.delete(task);
            return true;
        }).orElse(false);
    }

    public TaskResponseDTO convertToDTO(TaskModel task) {
        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getPriority().name(),
                task.getStatus().name(),
                task.getUsername()
        );
    }

}
