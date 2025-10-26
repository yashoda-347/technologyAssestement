package com.convergeX.technicalAssestement.technicalAssestement.dto;

import com.convergeX.technicalAssestement.technicalAssestement.dto.request_dto.RequestTaskDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestTaskDtoTest {

    private RequestTaskDto requestTaskDto;

    @BeforeEach
    void setUp() {
        requestTaskDto = RequestTaskDto.builder()
                .id("test-id")
                .title("Test Task")
                .description("Test Description")
                .build();
    }

    @Test
    void builder_ShouldCreateDtoWithAllFields() {
        assertNotNull(requestTaskDto);
        assertEquals("test-id", requestTaskDto.getId());
        assertEquals("Test Task", requestTaskDto.getTitle());
        assertEquals("Test Description", requestTaskDto.getDescription());
    }

    @Test
    void setters_ShouldUpdateFields() {
        requestTaskDto.setId("new-id");
        requestTaskDto.setTitle("New Title");
        requestTaskDto.setDescription("New Description");

        assertEquals("new-id", requestTaskDto.getId());
        assertEquals("New Title", requestTaskDto.getTitle());
        assertEquals("New Description", requestTaskDto.getDescription());
    }

    @Test
    void noArgsConstructor_ShouldCreateEmptyDto() {
        RequestTaskDto emptyDto = new RequestTaskDto();

        assertNull(emptyDto.getId());
        assertNull(emptyDto.getTitle());
        assertNull(emptyDto.getDescription());
    }

    @Test
    void allArgsConstructor_ShouldCreateDtoWithAllParameters() {
        RequestTaskDto allArgsDto = new RequestTaskDto("id", "title", "desc");

        assertEquals("id", allArgsDto.getId());
        assertEquals("title", allArgsDto.getTitle());
        assertEquals("desc", allArgsDto.getDescription());
    }
}


