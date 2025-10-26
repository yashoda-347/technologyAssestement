package com.convergeX.technicalAssestement.technicalAssestement.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StandardResponseDtoTest {

    private StandardResponseDto standardResponseDto;

    @BeforeEach
    void setUp() {
        standardResponseDto = new StandardResponseDto(200, "Success", "test data");
    }

    @Test
    void constructor_ShouldCreateResponseWithAllFields() {
        assertEquals(200, standardResponseDto.getCode());
        assertEquals("Success", standardResponseDto.getMessage());
        assertEquals("test data", standardResponseDto.getData());
    }

    @Test
    void setters_ShouldUpdateFields() {
        standardResponseDto.setCode(404);
        standardResponseDto.setMessage("Not Found");
        standardResponseDto.setData(null);

        assertEquals(404, standardResponseDto.getCode());
        assertEquals("Not Found", standardResponseDto.getMessage());
        assertNull(standardResponseDto.getData());
    }

    @Test
    void noArgsConstructor_ShouldCreateEmptyResponse() {
        StandardResponseDto emptyResponse = new StandardResponseDto();

        assertEquals(0, emptyResponse.getCode());
        assertNull(emptyResponse.getMessage());
        assertNull(emptyResponse.getData());
    }

    @Test
    void constructor_ShouldHandleNullValues() {
        StandardResponseDto nullResponse = new StandardResponseDto(500, null, null);

        assertEquals(500, nullResponse.getCode());
        assertNull(nullResponse.getMessage());
        assertNull(nullResponse.getData());
    }
}


