package com.convergeX.technicalAssestement.technicalAssestement.dto;

import com.convergeX.technicalAssestement.technicalAssestement.dto.response_dto.ResponseTaskDto;
import com.convergeX.technicalAssestement.technicalAssestement.enums.StatusType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTaskDtoTest {

    private ResponseTaskDto responseTaskDto;

    @BeforeEach
    void setUp() {
        responseTaskDto = ResponseTaskDto.builder()
                .id("test-id")
                .title("Test Task")
                .description("Test Description")
                .createdAt(LocalDateTime.now())
                .statusType(StatusType.TODO)
                .build();
    }

    @Test
    void builder_ShouldCreateDtoWithAllFields() {
        assertNotNull(responseTaskDto);
        assertEquals("test-id", responseTaskDto.getId());
        assertEquals("Test Task", responseTaskDto.getTitle());
        assertEquals("Test Description", responseTaskDto.getDescription());
        assertNotNull(responseTaskDto.getCreatedAt());
        assertEquals(StatusType.TODO, responseTaskDto.getStatusType());
    }

    @Test
    void setters_ShouldUpdateFields() {
        responseTaskDto.setId("new-id");
        responseTaskDto.setTitle("New Title");
        responseTaskDto.setDescription("New Description");
        responseTaskDto.setStatusType(StatusType.IN_PROGRESS);
        LocalDateTime newTime = LocalDateTime.now();
        responseTaskDto.setCreatedAt(newTime);

        assertEquals("new-id", responseTaskDto.getId());
        assertEquals("New Title", responseTaskDto.getTitle());
        assertEquals("New Description", responseTaskDto.getDescription());
        assertEquals(StatusType.IN_PROGRESS, responseTaskDto.getStatusType());
        assertEquals(newTime, responseTaskDto.getCreatedAt());
    }

    @Test
    void noArgsConstructor_ShouldCreateEmptyDto() {
        ResponseTaskDto emptyDto = new ResponseTaskDto();

        assertNull(emptyDto.getId());
        assertNull(emptyDto.getTitle());
        assertNull(emptyDto.getDescription());
        assertNull(emptyDto.getCreatedAt());
        assertNull(emptyDto.getStatusType());
    }

    @Test
    void allArgsConstructor_ShouldCreateDtoWithAllParameters() {
        LocalDateTime time = LocalDateTime.now();
        ResponseTaskDto allArgsDto = new ResponseTaskDto("id", "title", "desc", time, StatusType.DONE);

        assertEquals("id", allArgsDto.getId());
        assertEquals("title", allArgsDto.getTitle());
        assertEquals("desc", allArgsDto.getDescription());
        assertEquals(time, allArgsDto.getCreatedAt());
        assertEquals(StatusType.DONE, allArgsDto.getStatusType());
    }
}


