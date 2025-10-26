package com.convergeX.technicalAssestement.technicalAssestement.repo;

import com.convergeX.technicalAssestement.technicalAssestement.entity.Task;
import com.convergeX.technicalAssestement.technicalAssestement.enums.StatusType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TaskRepoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TaskRepo taskRepo;

    private Task task1;
    private Task task2;
    private Task task3;

    @BeforeEach
    void setUp() {
        task1 = Task.builder()
                .id("task-1")
                .title("First Task")
                .description("First Description")
                .status(StatusType.TODO)
                .createdTime(LocalDateTime.now().minusHours(3))
                .build();

        task2 = Task.builder()
                .id("task-2")
                .title("Second Task")
                .description("Second Description")
                .status(StatusType.IN_PROGRESS)
                .createdTime(LocalDateTime.now().minusHours(2))
                .build();

        task3 = Task.builder()
                .id("task-3")
                .title("Third Task")
                .description("Third Description")
                .status(StatusType.DONE)
                .createdTime(LocalDateTime.now().minusHours(1))
                .build();
    }

    @Test
    void findTop5ByOrderByCreatedTimeDesc_ShouldReturnTasksInDescendingOrder() {
        entityManager.persistAndFlush(task1);
        entityManager.persistAndFlush(task2);
        entityManager.persistAndFlush(task3);

        List<Task> result = taskRepo.findTop5ByOrderByCreatedTimeDesc();

        assertEquals(3, result.size());
        assertEquals("task-3", result.get(0).getId());
        assertEquals("task-2", result.get(1).getId());
        assertEquals("task-1", result.get(2).getId());
    }

    @Test
    void findTop5ByOrderByCreatedTimeDesc_ShouldReturnEmptyList_WhenNoTasks() {
        List<Task> result = taskRepo.findTop5ByOrderByCreatedTimeDesc();

        assertTrue(result.isEmpty());
    }

    @Test
    void findTop5ByOrderByCreatedTimeDesc_ShouldReturnOnlyFiveTasks_WhenMoreThanFiveExist() {
        for (int i = 1; i <= 7; i++) {
            Task task = Task.builder()
                    .id("task-" + i)
                    .title("Task " + i)
                    .description("Description " + i)
                    .status(StatusType.TODO)
                    .createdTime(LocalDateTime.now().minusMinutes(i))
                    .build();
            entityManager.persistAndFlush(task);
        }

        List<Task> result = taskRepo.findTop5ByOrderByCreatedTimeDesc();

        assertEquals(5, result.size());
        assertEquals("task-1", result.get(0).getId());
        assertEquals("task-5", result.get(4).getId());
    }

    @Test
    void save_ShouldPersistTask() {
        Task savedTask = taskRepo.save(task1);

        assertNotNull(savedTask.getId());
        assertEquals("First Task", savedTask.getTitle());
        assertEquals(StatusType.TODO, savedTask.getStatus());
    }

    @Test
    void findById_ShouldReturnTask_WhenExists() {
        Task savedTask = taskRepo.save(task1);
        entityManager.flush();

        Task foundTask = taskRepo.findById(savedTask.getId()).orElse(null);

        assertNotNull(foundTask);
        assertEquals(savedTask.getId(), foundTask.getId());
        assertEquals("First Task", foundTask.getTitle());
    }

    @Test
    void findById_ShouldReturnEmpty_WhenNotExists() {
        Task foundTask = taskRepo.findById("non-existent-id").orElse(null);

        assertNull(foundTask);
    }

    @Test
    void deleteById_ShouldRemoveTask() {
        Task savedTask = taskRepo.save(task1);
        entityManager.flush();

        taskRepo.deleteById(savedTask.getId());
        entityManager.flush();

        Task foundTask = taskRepo.findById(savedTask.getId()).orElse(null);
        assertNull(foundTask);
    }
}


