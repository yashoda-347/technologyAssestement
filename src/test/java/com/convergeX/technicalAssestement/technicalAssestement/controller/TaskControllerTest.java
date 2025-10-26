package com.convergeX.technicalAssestement.technicalAssestement.controller;

import com.convergeX.technicalAssestement.technicalAssestement.dto.request_dto.RequestTaskDto;
import com.convergeX.technicalAssestement.technicalAssestement.entity.Task;
import com.convergeX.technicalAssestement.technicalAssestement.enums.StatusType;
import com.convergeX.technicalAssestement.technicalAssestement.service.TaskService;
import com.convergeX.technicalAssestement.technicalAssestement.util.StandardResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    private RequestTaskDto requestTaskDto;
    private Task task;
    private List<Task> taskList;

    @BeforeEach
    void setUp() {
        requestTaskDto = RequestTaskDto.builder()
                .title("Test Task")
                .description("Test Description")
                .build();

        task = Task.builder()
                .id("test-id")
                .title("Test Task")
                .description("Test Description")
                .status(StatusType.TODO)
                .createdTime(LocalDateTime.now())
                .build();

        taskList = Arrays.asList(task);
    }

    @Test
    void create_ShouldReturnCreatedStatus_WhenValidRequest() throws Exception {
        doNothing().when(taskService).create(any(RequestTaskDto.class));

        mockMvc.perform(post("/api/tasks/task/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestTaskDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value(201))
                .andExpect(jsonPath("$.message").value("task saved"));
    }

    @Test
    void delete_ShouldReturnOkStatus_WhenTaskExists() throws Exception {
        doNothing().when(taskService).delete(anyString());

        mockMvc.perform(delete("/api/tasks/task/delete/test-id"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(204))
                .andExpect(jsonPath("$.message").value("Task deleted successfully"));
    }

    @Test
    void delete_ShouldReturnNotFoundStatus_WhenTaskNotFound() throws Exception {
        doThrow(new RuntimeException()).when(taskService).delete(anyString());

        mockMvc.perform(delete("/api/tasks/task/delete/non-existent-id"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void findLatestFive_ShouldReturnOkStatus_WithTaskList() throws Exception {
        when(taskService.findLatestFiveByUser()).thenReturn(taskList);

        mockMvc.perform(get("/api/tasks/task/get-latest-five/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.message").value("Data available"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].id").value("test-id"))
                .andExpect(jsonPath("$.data[0].title").value("Test Task"));
    }

    @Test
    void findLatestFive_ShouldReturnEmptyList_WhenNoTasks() throws Exception {
        when(taskService.findLatestFiveByUser()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/tasks/task/get-latest-five/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty());
    }
}
