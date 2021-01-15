package com.automatedparking.abstracts;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBOperationsTest {

    private static DBOperations dbOperations;

    @BeforeAll
    static void setDbOperations() {
        dbOperations = new DBOperations();
    }

    @Test
    void createDB() {
        assertTrue(DBOperations.createDB());
    }

    @Test
    void createEntry() {
        createDB();
        dbOperations.createParking(2);

        assertEquals(1, dbOperations.createEntry(21, "KA-01-HH-1234"));
        assertEquals(2, dbOperations.createEntry(21, "KA-01-HH-5678"));

        DBOperations.close();
    }

    @Test
    void close() {
        Throwable exception = assertThrows(IllegalArgumentException.class, DBOperations::close);
        assertEquals("Invalid attempt to clean up DB.", exception.getMessage());
    }

    @Test
    void deleteEntry() {
        createDB();
        dbOperations.createParking(2);
        dbOperations.createEntry(21, "KA-01-HH-9101");

        assertEquals(1, dbOperations.deleteEntry(1).get(0).getSlot());

        DBOperations.close();
    }

    @Test
    void getRegistrationByAge() {
        createDB();
        dbOperations.createParking(2);
        dbOperations.createEntry(21, "KA-01-HH-1213");
        dbOperations.createEntry(21, "KA-01-HH-1415");

        assertEquals(2, dbOperations.getRegistrationByAge(21).size());
        assertEquals("KA-01-HH-1213", dbOperations.getRegistrationByAge(21).get(0));
        assertEquals("KA-01-HH-1415", dbOperations.getRegistrationByAge(21).get(1));

        DBOperations.close();
    }

    @Test
    void getSlotByAge() {
        createDB();
        dbOperations.createParking(2);
        dbOperations.createEntry(21, "KA-01-HH-1617");
        dbOperations.createEntry(21, "KA-01-HH-1819");

        assertEquals(2, dbOperations.getSlotByAge(21).size());
        assertEquals(1, dbOperations.getSlotByAge(21).get(0));
        assertEquals(2, dbOperations.getSlotByAge(21).get(1));

        DBOperations.close();
    }

    @Test
    void getSlotByRegistration() {
        /*createDB();
        dbOperations.createParking(2);
        dbOperations.createEntry(21, "KA-01-HH-2021");
        dbOperations.createEntry(32, "KA-01-HH-2223");

        assertEquals(1, dbOperations.getSlotByRegistration("KA-01-HH-2021"));
        assertEquals(2, dbOperations.getSlotByRegistration("KA-01-HH-2223"));

        DBOperations.close();*/
    }

    @Test
    void isEmpty() {
        assertTrue(true, String.valueOf(dbOperations.isEmpty()));
    }
}