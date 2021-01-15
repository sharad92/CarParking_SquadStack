package com.automatedparking.operations;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlotNumberByAgeTest {

    private static SlotNumberByAge slotNumberByAge;
    @BeforeAll
    static void setSlotNumberByAge() {
        slotNumberByAge = new SlotNumberByAge();
    }

    @Test
    void validateAndExecute() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                slotNumberByAge.validateAndExecute(null));
        assertEquals("Operation string invalid.", exception.getMessage());
    }
}