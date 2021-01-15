package com.automatedparking.operations;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkTest {

    private static Park park;
    @BeforeAll
    static void setPark() {
        park = new Park();
    }

    @Test
    void validateAndExecute() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                park.validateAndExecute(null));
        assertEquals("Operation string invalid.", exception.getMessage());
    }
}