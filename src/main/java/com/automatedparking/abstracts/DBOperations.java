package com.automatedparking.abstracts;

import com.automatedparking.models.Parking;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

/**
 * Implementation of a super class providing a common place for all DB operations.
 */
public class DBOperations {
    private static final Logger logger = LogManager.getLogger(DBOperations.class);

    private static int currentSlot = 1;
    private static int previousSlot = -1;
    private static int size;
    private static Nitrite db;
    private static ObjectRepository<Parking> repository;
    private static int[] queueList;

    /**
     * Creates a DB instance and initializes an Object repository to store structure data.
     */
    public static boolean createDB() {
        logger.debug("DB creation in progress.");

        try {
            File file = new File("ParkingStructure.db");
            if (file.exists())
                //noinspection ResultOfMethodCallIgnored
                file.delete();

            db = Nitrite.builder()
                    .compressed()
                    .filePath("ParkingStructure.db")
                    .openOrCreate("admin", "admin");

            repository = db.getRepository("slot", Parking.class);
        } catch (Exception excp) {
            logger.error("Db creation failed with error : {}", excp.getMessage());
            throw excp;
        }

        return true;
    }

    /**
     * Closes and nullifies all DB instances.
     */
    public static void close() {
        logger.debug("Closing DB instance and cleaning up additional items.");

        try {
            db.close();
            db = null;
            repository = null;
            queueList = null;

            File file = new File("ParkingStructure.db");
            if (file.exists())
                //noinspection ResultOfMethodCallIgnored
                file.delete();
        } catch (Exception excp) {
            logger.error("Db close operation failed with error : {}", excp.getMessage());
            throw new IllegalArgumentException("Invalid attempt to clean up DB.");
        }
    }

    /**
     * Recalculates and updates the parking slot for next insert operation.
     */
    private void calculateNextSlot() {
        logger.debug("Calculating next slot open for assignment.");

        previousSlot = currentSlot;
        for (int i = 0; i < queueList.length; i++) {
            if (queueList[i] == 0) {
                currentSlot = i + 1;
                break;
            }
        }
    }

    /**
     * Updates the size of the parking structure
     * and initializes a queue list to maintain occupied slots.
     *
     * @param sizeVal : size of the parking structure.
     */
    protected void createParking(int sizeVal) {
        logger.debug("Updating size of the parking structure.");

        size = sizeVal;
        queueList = new int[sizeVal];
    }

    /**
     * Creates an instance of a new parked vehicle and stores it in the DB.
     *
     * @param age          : age of the driver.
     * @param registration : registration number of the car.
     * @return The slot where the car got parked.
     */
    protected int createEntry(int age, String registration) {
        logger.debug("Assigning parking spot to newly arrived vehicle.");

        try {
            if (size > 0 && currentSlot > size) {
                System.out.println("Parking lot full.");
                return -1;
            }

            Parking parking = new Parking(age, registration);
            parking.setSlot(currentSlot);
            repository.insert(parking);

            queueList[currentSlot - 1] = 1;
            calculateNextSlot();
        } catch (Exception excp) {
            logger.error("Slot assignment failed with error : {}", excp.getMessage());
            if(excp instanceof IllegalArgumentException)
                throw new IllegalArgumentException("Invalid arguments provided");
            else
                throw new IllegalArgumentException("Slot already in use, cannot reassign while taken.");
        }

        return (previousSlot);
    }

    /**
     * Removes the instance of vehicle parking at the provided slot.
     *
     * @param slot : slot from where the car is leaving.
     * @return Instance of vehicle that left.
     */
    protected List<Parking> deleteEntry(int slot) {
        logger.debug("Vehicle exiting parking, entries being updated.");
        List<Parking> parkingVacated = new ArrayList<>();

        try {
            Cursor<Parking> cursor = repository.find(ObjectFilters.eq("slot", slot));
            if(cursor.size() == 0)
                System.out.println("No vehicle parked at slot : " + slot);

            for (Parking vehicle : cursor) {
                parkingVacated.add(vehicle);
            }

            repository.remove(eq("slot", slot));
            queueList[slot - 1] = 0;
            calculateNextSlot();
        } catch (Exception excp) {
            logger.error("Slot vacation failed with error : {}", excp.getMessage());
            if(excp instanceof IllegalArgumentException)
                throw new IllegalArgumentException("Invalid arguments provided.");
            else
                throw new RuntimeException("Processing error encountered, please call support.");
        }

        return parkingVacated;
    }

    /**
     * Returns a list of all registration numbers based on matching age.
     *
     * @param age : age of the driver
     * @return List of all registration numbers where the age of driver matches the provided input.
     */
    protected List<String> getRegistrationByAge(int age) {
        logger.debug("Finding registration of cars based on the age of driver.");
        List<String> slots = new ArrayList<>();

        try {
            Cursor<Parking> cursor = repository.find(ObjectFilters.eq("age", age));
            for (Parking vehicle : cursor) {
                slots.add(vehicle.getRegistration());
            }
        } catch (Exception excp) {
            logger.error("Operation {getRegistrationByAge} failed with error : {}", excp.getMessage());
        }

        return slots;
    }

    /**
     * Returns a list of all slot numbers based on matching age.
     *
     * @param age : age of the driver
     * @return List of all slot numbers where the age of driver matches the provided input.
     */
    protected List<Integer> getSlotByAge(int age) {
        logger.debug("Finding slot assigned to a car based on the age of driver.");
        List<Integer> slots = new ArrayList<>();

        try {
            Cursor<Parking> cursor = repository.find(ObjectFilters.eq("age", age));
            for (Parking vehicle : cursor) {
                slots.add(vehicle.getSlot());
            }
        } catch (Exception excp) {
            logger.error("Operation {getSlotByAge} failed with error : {}", excp.getMessage());
        }

        return slots;
    }

    /**
     * Returns the slot number based on matching registration number.
     *
     * @param registration : registration number of the vehicle.
     * @return Slot number of the vehicle matching the provided registration number.
     */
    protected int getSlotByRegistration(String registration) {
        logger.debug("Finding slot assigned to a car based on the registration number.");

        try {
            Cursor<Parking> cursor = repository.find(ObjectFilters.eq("registration", registration));
            for (Parking vehicle : cursor) {
                return vehicle.getSlot();
            }
        } catch (Exception excp) {
            logger.error("Operation {getSlotByRegistration} failed with error : {}", excp.getMessage());
        }

        return -1;
    }

    /**
     * Checks and return a boolean based on if the repository exists or is empty.
     */
    protected boolean isEmpty() {
        logger.debug("Checking for empty or null Object repository.");

        return (repository == null || repository.size() == 0);
    }
}
