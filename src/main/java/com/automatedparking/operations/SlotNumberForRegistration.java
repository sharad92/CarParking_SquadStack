package com.automatedparking.operations;

import com.automatedparking.abstracts.DBOperations;
import com.automatedparking.interfaces.Operation;

/**
 * Implementation of "Slot_number_for_car_with_number"
 * operation to return all vehicle slot numbers where the cars match the registration number.
 */
public class SlotNumberForRegistration extends DBOperations implements Operation {

    @Override
    public void validateAndExecute(String operationString) {

        //Validate the operation string
        if(operationString == null || operationString.length() == 0)
            throw new IllegalArgumentException("Operation string invalid.");

        //Execute operation
        String registration = operationString
                .substring(operationString.lastIndexOf(" ") + 1);
        int slot = getSlotByRegistration(registration);

        if (slot != -1)
            System.out.println(slot);
        else
            System.out.println("No vehicle present with registration " + registration);
    }
}
