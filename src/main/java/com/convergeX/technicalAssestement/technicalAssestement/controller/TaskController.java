package com.convergeX.technicalAssestement.technicalAssestement.controller;


import com.convergeX.technicalAssestement.technicalAssestement.dto.request_dto.RequestTaskDto;
import com.convergeX.technicalAssestement.technicalAssestement.service.TaskService;
import com.convergeX.technicalAssestement.technicalAssestement.util.StandardResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:3001")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/task/create")
    public ResponseEntity<StandardResponseDto> create(@RequestBody RequestTaskDto dto) {
        taskService.create(dto);
        return new ResponseEntity<>(
                new StandardResponseDto(201,"task saved", null), HttpStatus.CREATED
        );
    }

    @DeleteMapping("/task/delete/{id}")
    public ResponseEntity<StandardResponseDto> delete(@PathVariable("id") String taskId) throws Exception {
        taskService.delete(taskId);
        return ResponseEntity.ok(
                new StandardResponseDto(
                        204,
                        "Task deleted successfully",
                        null
                )
        );
    }

    @GetMapping("/task/get-latest-five/")
    public ResponseEntity<StandardResponseDto> findLatestFive() throws SQLException {
        return new ResponseEntity<>(
                new StandardResponseDto(
                        200,
                        "Data available",
                        taskService.findLatestFiveByUser()
                ),
                HttpStatus.OK
        );
    }



}
