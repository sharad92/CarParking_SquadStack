package com.automatedparking.operations;

import com.automatedparking.abstracts.DBOperations;
import com.automatedparking.parsers.OperationTypeParser;
import org.dizitart.no2.Nitrite;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateParkingTest {

    private static CreateParking createParking;
    @BeforeAll
    static void setCreateParking() {
        createParking = new CreateParking();
    }

    @Test
    void validateAndExecute() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                createParking.validateAndExecute(null));
        assertEquals("Operation string invalid.", exception.getMessage());

        assertThrows(IllegalArgumentException.class, () ->
                createParking.validateAndExecute("Dummy Data"));
    }
}