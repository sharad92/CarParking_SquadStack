package com.automatedparking.operations;

import com.automatedparking.abstracts.DBOperations;
import com.automatedparking.interfaces.Operation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of "Create_parking_lot" operation
 * allowing creation of the parking lot structure.
 */
public class CreateParking extends DBOperations implements Operation {
    private static final Logger logger = LogManager.getLogger(CreateParking.class);

    @Override
    public void validateAndExecute(String operationString) {

        //Validate the operation string
        if (operationString == null || operationString.length() == 0)
            throw new IllegalArgumentException("Operation string invalid.");

        //Execute operation
        if (isEmpty()) {
            createParking(Integer.parseInt(
                    operationString.substring(operationString.lastIndexOf(" ") + 1)));
            System.out.println("Parking structure created.");
        } else {
            System.out.println("Invalid operation, parking already exists.");
            throw new IllegalArgumentException();
        }
    }
}
