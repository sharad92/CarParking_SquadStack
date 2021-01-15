package com.automatedparking.operations;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeaveTest {

    private static Leave leave;
    @BeforeAll
    static void setLeave() {
        leave = new Leave();
    }

    @Test
    void validateAndExecute() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                leave.validateAndExecute(null));
        assertEquals("Operation string invalid.", exception.getMessage());
    }
}