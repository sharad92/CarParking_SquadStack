package com.automatedparking.operations;

import com.automatedparking.abstracts.DBOperations;
import com.automatedparking.interfaces.Operation;
import com.automatedparking.models.Parking;

import java.util.List;

/**
 * Implementation of "Leave" operation
 * representing a vehicle vacating their parking spot.
 */
public class Leave extends DBOperations implements Operation {

    @Override
    public void validateAndExecute(String operationString) {

        //Validate the operation string
        if(operationString == null || operationString.length() == 0)
            throw new IllegalArgumentException("Operation string invalid.");

        //Execute operation
        int slot = Integer.parseInt(operationString.substring(
                operationString.lastIndexOf(" ") + 1));
        List<Parking> parkingVacated = deleteEntry(slot);

        for (Parking details : parkingVacated) {
            System.out.println("Slot number "
                    + details.getSlot() +
                    " vacated, the car with vehicle registration number "
                    + details.getRegistration() +
                    " left the space, the driver of the car was of age "
                    + details.getAge());
        }
    }
}
