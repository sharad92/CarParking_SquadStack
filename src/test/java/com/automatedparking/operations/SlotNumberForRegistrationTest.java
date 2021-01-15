package com.automatedparking.operations;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlotNumberForRegistrationTest {

    private static SlotNumberForRegistration slotNumberForRegistration;
    @BeforeAll
    static void setSlotNumberForRegistration() {
        slotNumberForRegistration = new SlotNumberForRegistration();
    }

    @Test
    void validateAndExecute() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                slotNumberForRegistration.validateAndExecute(null));
        assertEquals("Operation string invalid.", exception.getMessage());
    }
}