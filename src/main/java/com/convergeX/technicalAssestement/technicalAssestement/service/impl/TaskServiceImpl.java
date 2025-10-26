package com.convergeX.technicalAssestement.technicalAssestement.service.impl;


import com.convergeX.technicalAssestement.technicalAssestement.dto.request_dto.RequestTaskDto;
import com.convergeX.technicalAssestement.technicalAssestement.entity.Task;
import com.convergeX.technicalAssestement.technicalAssestement.enums.StatusType;
import com.convergeX.technicalAssestement.technicalAssestement.exceptions.EntryNotFoundException;
import com.convergeX.technicalAssestement.technicalAssestement.repo.TaskRepo;
import com.convergeX.technicalAssestement.technicalAssestement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepo task_repo;
    @Override
    public void create(RequestTaskDto dto) {
        try {
            task_repo.save(toTask(dto));
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    @Override
    public void delete(String taskId) throws Exception {
        task_repo.findById(taskId).orElseThrow(()-> new EntryNotFoundException("Task not found"));
        task_repo.deleteById(taskId);
    }
    @Override
    public List<Task> findLatestFiveByUser() {
        return task_repo.findTop5ByOrderByCreatedTimeDesc();
    }



    // converting request task dto to save in the table
    private Task toTask(RequestTaskDto dto) throws SQLException {
        return dto == null ? null :
                Task.builder()
                        .id(UUID.randomUUID().toString())
                        .title(dto.getTitle())
                        .description(dto.getDescription())
                        .status(StatusType.TODO)
                        .createdTime(LocalDateTime.now())
                        .build();
    }


}
