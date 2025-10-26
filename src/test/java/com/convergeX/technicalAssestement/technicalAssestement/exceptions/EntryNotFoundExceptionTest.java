package com.convergeX.technicalAssestement.technicalAssestement.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntryNotFoundExceptionTest {

    @Test
    void constructor_ShouldCreateExceptionWithMessage() {
        String message = "Task not found";
        EntryNotFoundException exception = new EntryNotFoundException(message);

        assertEquals(message, exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void constructor_ShouldCreateExceptionWithNullMessage() {
        EntryNotFoundException exception = new EntryNotFoundException(null);

        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void constructor_ShouldCreateExceptionWithEmptyMessage() {
        EntryNotFoundException exception = new EntryNotFoundException("");

        assertEquals("", exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    void exception_ShouldBeInstanceOfRuntimeException() {
        EntryNotFoundException exception = new EntryNotFoundException("test");

        assertTrue(exception instanceof RuntimeException);
    }
}


