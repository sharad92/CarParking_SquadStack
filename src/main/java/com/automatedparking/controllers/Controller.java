package com.automatedparking.controllers;

import com.automatedparking.abstracts.DBOperations;
import com.automatedparking.parsers.OperationTypeParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.reflect.annotation.ExceptionProxy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class Controller {
    private static final Logger logger = LogManager.getLogger(Controller.class);

    public static void main(String[] args) {
        logger.info("\n---------- {} ----------\n",
                ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME));

        if (args.length == 0) {
            System.out.println("No input file path provided, append the path and try again.");
            logger.error("No input file found, argument size 0.");
        } else {
            File inputFile = new File(args[0]);
            if (!inputFile.exists()) {
                System.out.println("Invalid input file path provided, validate the path and try again.");
                logger.error("Input file path provided is incorrect, path provided is {}", args[0]);
            }

            logger.info("Input file path : {}", args[0]);
            OperationTypeParser operationTypeParser = OperationTypeParser.getInstance();
            try (Stream<String> stream = Files.lines(inputFile.toPath())) {
                stream.forEach(operationString ->
                        operationTypeParser
                                .translateToOperation(operationString)
                                .validateAndExecute(operationString)
                );
            } catch (Exception excp) {
                System.out.println("Invalid command present in input file, correct the file and try again.");
                logger.error("Execution failed due to invalid command in input file, " +
                        "possible corruption of future commands hence terminating : {}", excp.getMessage());
            }

            //Close DB
            DBOperations.close();
        }

    }
}
