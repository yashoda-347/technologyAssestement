package com.convergeX.technicalAssestement.technicalAssestement.entity;

import com.convergeX.technicalAssestement.technicalAssestement.enums.StatusType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private Task task;

    @BeforeEach
    void setUp() {
        task = Task.builder()
                .id("test-id")
                .title("Test Task")
                .description("Test Description")
                .status(StatusType.TODO)
                .createdTime(LocalDateTime.now())
                .build();
    }

    @Test
    void builder_ShouldCreateTaskWithAllFields() {
        assertNotNull(task);
        assertEquals("test-id", task.getId());
        assertEquals("Test Task", task.getTitle());
        assertEquals("Test Description", task.getDescription());
        assertEquals(StatusType.TODO, task.getStatus());
        assertNotNull(task.getCreatedTime());
    }

    @Test
    void setters_ShouldUpdateFields() {
        task.setId("new-id");
        task.setTitle("New Title");
        task.setDescription("New Description");
        task.setStatus(StatusType.IN_PROGRESS);
        LocalDateTime newTime = LocalDateTime.now();
        task.setCreatedTime(newTime);

        assertEquals("new-id", task.getId());
        assertEquals("New Title", task.getTitle());
        assertEquals("New Description", task.getDescription());
        assertEquals(StatusType.IN_PROGRESS, task.getStatus());
        assertEquals(newTime, task.getCreatedTime());
    }

    @Test
    void noArgsConstructor_ShouldCreateEmptyTask() {
        Task emptyTask = new Task();

        assertNull(emptyTask.getId());
        assertNull(emptyTask.getTitle());
        assertNull(emptyTask.getDescription());
        assertNull(emptyTask.getStatus());
        assertNull(emptyTask.getCreatedTime());
    }

    @Test
    void allArgsConstructor_ShouldCreateTaskWithAllParameters() {
        LocalDateTime time = LocalDateTime.now();
        Task allArgsTask = new Task("id", "title", "desc", time, StatusType.DONE);

        assertEquals("id", allArgsTask.getId());
        assertEquals("title", allArgsTask.getTitle());
        assertEquals("desc", allArgsTask.getDescription());
        assertEquals(time, allArgsTask.getCreatedTime());
        assertEquals(StatusType.DONE, allArgsTask.getStatus());
    }
}


