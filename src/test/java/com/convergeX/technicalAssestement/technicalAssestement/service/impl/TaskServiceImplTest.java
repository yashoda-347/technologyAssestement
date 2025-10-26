package com.convergeX.technicalAssestement.technicalAssestement.service.impl;

import com.convergeX.technicalAssestement.technicalAssestement.dto.request_dto.RequestTaskDto;
import com.convergeX.technicalAssestement.technicalAssestement.entity.Task;
import com.convergeX.technicalAssestement.technicalAssestement.enums.StatusType;
import com.convergeX.technicalAssestement.technicalAssestement.exceptions.EntryNotFoundException;
import com.convergeX.technicalAssestement.technicalAssestement.repo.TaskRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepo task_repo;

    @InjectMocks
    private TaskServiceImpl taskService;

    private RequestTaskDto requestTaskDto;
    private Task task;

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
    }

    @Test
    void create_ShouldSaveTask_WhenValidRequest() {
        when(task_repo.save(any(Task.class))).thenReturn(task);

        taskService.create(requestTaskDto);

        verify(task_repo, times(1)).save(any(Task.class));
    }

    @Test
    void create_ShouldThrowRuntimeException_WhenSQLException() {
        when(task_repo.save(any(Task.class))).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> taskService.create(requestTaskDto));
    }

    @Test
    void delete_ShouldDeleteTask_WhenTaskExists() {
        when(task_repo.findById("test-id")).thenReturn(Optional.of(task));
        doNothing().when(task_repo).deleteById("test-id");

        assertDoesNotThrow(() -> taskService.delete("test-id"));

        verify(task_repo, times(1)).findById("test-id");
        verify(task_repo, times(1)).deleteById("test-id");
    }

    @Test
    void delete_ShouldThrowEntryNotFoundException_WhenTaskNotFound() {
        when(task_repo.findById("non-existent-id")).thenReturn(Optional.empty());

        assertThrows(EntryNotFoundException.class, () -> taskService.delete("non-existent-id"));

        verify(task_repo, times(1)).findById("non-existent-id");
        verify(task_repo, never()).deleteById(any());
    }

    @Test
    void findLatestFiveByUser_ShouldReturnLatestFiveTasks() {
        List<Task> expectedTasks = Arrays.asList(task);
        when(task_repo.findTop5ByOrderByCreatedTimeDesc()).thenReturn(expectedTasks);

        List<Task> result = taskService.findLatestFiveByUser();

        assertEquals(expectedTasks, result);
        verify(task_repo, times(1)).findTop5ByOrderByCreatedTimeDesc();
    }

    @Test
    void findLatestFiveByUser_ShouldReturnEmptyList_WhenNoTasks() {
        when(task_repo.findTop5ByOrderByCreatedTimeDesc()).thenReturn(Arrays.asList());

        List<Task> result = taskService.findLatestFiveByUser();

        assertTrue(result.isEmpty());
        verify(task_repo, times(1)).findTop5ByOrderByCreatedTimeDesc();
    }
}


