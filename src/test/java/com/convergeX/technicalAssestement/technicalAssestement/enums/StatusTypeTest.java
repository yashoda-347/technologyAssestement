package com.convergeX.technicalAssestement.technicalAssestement.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusTypeTest {

    @Test
    void enum_ShouldHaveCorrectValues() {
        StatusType[] values = StatusType.values();

        assertEquals(3, values.length);
        assertEquals(StatusType.TODO, values[0]);
        assertEquals(StatusType.IN_PROGRESS, values[1]);
        assertEquals(StatusType.DONE, values[2]);
    }

    @Test
    void valueOf_ShouldReturnCorrectEnum() {
        assertEquals(StatusType.TODO, StatusType.valueOf("TODO"));
        assertEquals(StatusType.IN_PROGRESS, StatusType.valueOf("IN_PROGRESS"));
        assertEquals(StatusType.DONE, StatusType.valueOf("DONE"));
    }

    @Test
    void valueOf_ShouldThrowException_WhenInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> StatusType.valueOf("INVALID"));
    }

    @Test
    void enum_ShouldHaveCorrectStringRepresentation() {
        assertEquals("TODO", StatusType.TODO.toString());
        assertEquals("IN_PROGRESS", StatusType.IN_PROGRESS.toString());
        assertEquals("DONE", StatusType.DONE.toString());
    }
}


