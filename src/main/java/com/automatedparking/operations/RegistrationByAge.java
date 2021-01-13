package com.automatedparking.operations;

import com.automatedparking.abstracts.DBOperations;
import com.automatedparking.interfaces.Operation;

import java.util.List;

/**
 * Implementation of "Vehicle_registration_number_for_driver_of_age"
 * operation to return all vehicle registration numbers where the drivers match the age criteria.
 */
public class RegistrationByAge extends DBOperations implements Operation {

    @Override
    public void validateAndExecute(String operationString) {

        //Validate the operation string
        if(operationString == null || operationString.length() == 0)
            throw new IllegalArgumentException("Operation string invalid.");

        //Execute operation
        int age = Integer.parseInt(
                operationString.substring(operationString.lastIndexOf(" ") + 1));
        List<String> registrations = getRegistrationByAge(age);

        for (String registration : registrations) {
            if (!registration.equals(registrations.get(registrations.size() - 1)))
                System.out.print(registration + ", ");
            else
                System.out.println(registration);
        }
    }
}
