package com.automatedparking.interfaces;

public interface Operation {

    /**
     * Common operation that validates the provided
     * operation string and executes the appropriate command.
     *
     * @param operationString : operation to be performed.
     */
    void validateAndExecute(String operationString);
}
