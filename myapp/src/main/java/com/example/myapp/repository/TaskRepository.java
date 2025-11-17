package com.example.myapp.repository;
import com.example.myapp.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long> {
    List<TaskModel> findByUsername(String username);


}
