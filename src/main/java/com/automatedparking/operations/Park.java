package com.automatedparking.operations;

import com.automatedparking.abstracts.DBOperations;
import com.automatedparking.interfaces.Operation;

/**
 * Implementation of "Park" operation
 * representing a vehicle being assigned a parking spot.
 */
public class Park extends DBOperations implements Operation {

    @Override
    public void validateAndExecute(String operationString) {

        //Validate the operation string
        if(operationString == null || operationString.length() == 0)
            throw new IllegalArgumentException("Operation string invalid.");

        //Execute operation
        String[] inputArr = operationString.trim().split(" ");
        int parkedAt = createEntry(Integer.parseInt(inputArr[inputArr.length - 1]), inputArr[1]);

        if (parkedAt > 0) {
            System.out.println("Car with vehicle registration number "
                    + inputArr[1]
                    + " has been parked at slot number "
                    + parkedAt
            );
        } else {
            System.out.println("Parking full.");
        }
    }
}
