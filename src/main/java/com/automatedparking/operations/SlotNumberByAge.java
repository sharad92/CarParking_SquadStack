package com.automatedparking.operations;

import com.automatedparking.abstracts.DBOperations;
import com.automatedparking.interfaces.Operation;

import java.util.List;

/**
 * Implementation of "Slot_numbers_for_driver_of_age"
 * operation to return all vehicle slot numbers where the drivers match the age criteria.
 */
public class SlotNumberByAge extends DBOperations implements Operation {

    @Override
    public void validateAndExecute(String operationString) {

        //Validate the operation string
        if(operationString == null || operationString.length() == 0)
            throw new IllegalArgumentException("Operation string invalid.");

        //Execute operation
        int age = Integer.parseInt(
                operationString.substring(operationString.lastIndexOf(" ") + 1));
        List<Integer> slots = getSlotByAge(age);

        for (int slot : slots) {
            if (slot != slots.get(slots.size() - 1))
                System.out.print(slot + ", ");
            else
                System.out.println(slot);
        }
    }
}
