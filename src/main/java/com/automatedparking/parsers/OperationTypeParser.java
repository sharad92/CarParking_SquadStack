package com.automatedparking.parsers;

import com.automatedparking.abstracts.DBOperations;
import com.automatedparking.interfaces.Operation;
import com.automatedparking.operations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation of parser class to return instance of appropriate operation class.
 */
public class OperationTypeParser {
    private static final Logger logger = LogManager.getLogger(OperationTypeParser.class);

    private static OperationTypeParser operationTypeParser;

    public OperationTypeParser() {
        DBOperations.createDB();
    }

    public static OperationTypeParser getInstance() {
        if (operationTypeParser == null) {
            operationTypeParser = new OperationTypeParser();
        }

        return operationTypeParser;
    }

    /**
     * This method is used to convert an operation string to an instance of
     * corresponding operation class returned as Base class Interface instance.
     *
     * @param operationString : string describing operation to be performed.
     */
    public Operation translateToOperation(String operationString) {

        Operations operationType = Operations
                .valueOf(operationString.substring(0, operationString.indexOf(" ")));

        Operation op = null;
        switch (operationType) {
            case Create_parking_lot: {
                op = new CreateParking();
                break;
            }

            case Park: {
                op = new Park();
                break;
            }

            case Slot_numbers_for_driver_of_age: {
                op = new SlotNumberByAge();
                break;
            }

            case Slot_number_for_car_with_number: {
                op = new SlotNumberForRegistration();
                break;
            }

            case Leave: {
                op = new Leave();
                break;
            }

            case Vehicle_registration_number_for_driver_of_age: {
                op = new RegistrationByAge();
                break;
            }

            default: {
                System.out.println("Invalid command : " + operationString
                        .substring(0, operationString.indexOf(" ")));
                break;
            }
        }

        logger.debug("Operation being executed : {}", operationType.toString());
        return op;
    }

    /**
     * Enums of all possible operations that can be performed.
     */
    enum Operations {
        Create_parking_lot,
        Park,
        Slot_numbers_for_driver_of_age,
        Slot_number_for_car_with_number,
        Leave,
        Vehicle_registration_number_for_driver_of_age
    }
}
