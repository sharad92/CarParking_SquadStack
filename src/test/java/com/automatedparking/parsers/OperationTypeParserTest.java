package com.automatedparking.parsers;

import com.automatedparking.interfaces.Operation;
import com.automatedparking.operations.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OperationTypeParserTest {

    private static OperationTypeParser operationTypeParser;

    @BeforeAll
    static void setOperationTypeParser() {
        operationTypeParser = OperationTypeParser.getInstance();
    }

    @Test
    void getInstance() {
        assertEquals(operationTypeParser, OperationTypeParser.getInstance());
    }

    @Test
    void translateToOperation() {
        Operation createParking = operationTypeParser.translateToOperation("Create_parking_lot 6");
        assertTrue(createParking instanceof CreateParking);

        Operation Leave = operationTypeParser.translateToOperation("Leave 2");
        assertTrue(Leave instanceof Leave);

        Operation Park = operationTypeParser.translateToOperation("Park KA-01-HH-1234 driver_age 21");
        assertTrue(Park instanceof Park);

        Operation RegistrationByAge = operationTypeParser.translateToOperation(
                "Vehicle_registration_number_for_driver_of_age 18");
        assertTrue(RegistrationByAge instanceof RegistrationByAge);

        Operation SlotNumberByAge = operationTypeParser.translateToOperation(
                "Slot_numbers_for_driver_of_age 40");
        assertTrue(SlotNumberByAge instanceof SlotNumberByAge);

        Operation SlotNumberForRegistration = operationTypeParser.translateToOperation(
                "Slot_number_for_car_with_number PB-01-HH-1234");
        assertTrue(SlotNumberForRegistration instanceof SlotNumberForRegistration);
    }
}