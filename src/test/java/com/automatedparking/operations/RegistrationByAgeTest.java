package com.automatedparking.operations;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationByAgeTest {

    private static RegistrationByAge registrationByAge;
    @BeforeAll
    static void setRegistrationByAge() {
        registrationByAge = new RegistrationByAge();
    }

    @Test
    void validateAndExecute() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                registrationByAge.validateAndExecute(null));
        assertEquals("Operation string invalid.", exception.getMessage());
    }
}