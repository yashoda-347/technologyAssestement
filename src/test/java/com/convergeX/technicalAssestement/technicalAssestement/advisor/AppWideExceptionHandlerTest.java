package com.convergeX.technicalAssestement.technicalAssestement.advisor;

import com.convergeX.technicalAssestement.technicalAssestement.exceptions.EntryNotFoundException;
import com.convergeX.technicalAssestement.technicalAssestement.util.StandardResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class AppWideExceptionHandlerTest {

    private AppWideExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new AppWideExceptionHandler();
    }

    @Test
    void handleEntryNotFoundException_ShouldReturnNotFoundResponse() {
        String errorMessage = "Task not found";
        EntryNotFoundException exception = new EntryNotFoundException(errorMessage);

        ResponseEntity<StandardResponseDto> response = exceptionHandler.handleEntryNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(404, response.getBody().getCode());
        assertEquals(errorMessage, response.getBody().getMessage());
        assertEquals(exception, response.getBody().getData());
    }

    @Test
    void handleEntryNotFoundException_ShouldReturnCorrectResponseStructure() {
        EntryNotFoundException exception = new EntryNotFoundException("Custom error message");

        ResponseEntity<StandardResponseDto> response = exceptionHandler.handleEntryNotFoundException(exception);

        StandardResponseDto responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(404, responseBody.getCode());
        assertEquals("Custom error message", responseBody.getMessage());
        assertSame(exception, responseBody.getData());
    }

    @Test
    void handleEntryNotFoundException_ShouldHandleNullMessage() {
        EntryNotFoundException exception = new EntryNotFoundException(null);

        ResponseEntity<StandardResponseDto> response = exceptionHandler.handleEntryNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(404, response.getBody().getCode());
        assertNull(response.getBody().getMessage());
    }
}


