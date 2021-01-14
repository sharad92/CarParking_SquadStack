# **Automated Car Parking**
This is an implementation of a parking structure with an automated ticketing system that provides interaction free 
parking facility. 

## **Problem Statement && Details**
Build a parking lot that can hold upto 'N' cars at any given point in time. Each vehicle is allocated a slot number 
starting from 1 which increases with distance from entry in increments of 1. This system has to work in an automated 
manner without any human intervention providing parking spots and handling every incoming vehicle according to the 
bounds of the system.

The parameters to provide a parking spot are as follows :
* Upon arrival of a vehicle the registration number of the vehicle and the age of the driver has to be logged.
* The closest available slot should be assigned to the vehicle considering availability.
* Upon exit of the vehicle the slot is to be marked as free and added back to be considered for allocation.

Certain query operation are also to be provided by the system that allow for monitoring of the parking lot, which are :
* Vehicle registration of every parked car to be provided given the age of the driver.
* Parking slot allocated to a vehicle to be provided given the registration number.
* Parking slot allocated to a vehicle to be provided given the age of the driver.

## **Getting Started**
Detailed below are the steps required to set up and run the project.

### **Dependencies**
```
* Java 8
* Maven
```

### **Dependency Installation on Windows**
* [Java Installation](https://docs.oracle.com/javase/10/install/installation-jdk-and-jre-microsoft-windows-platforms.htm#JSJIG-GUID-DAF345BA-B3E7-4CF2-B87A-B6662D691840)
* [Maven Installation](https://mkyong.com/maven/how-to-install-maven-in-windows/)

### **Dependency Installation on Unix**
* [Java Installation](https://docs.oracle.com/javase/10/install/installation-jdk-and-jre-linux-platforms.htm#JSJIG-GUID-4907E1A6-7B4B-4E98-9DA5-BF2A4D01AA57)
* [Maven Installation](https://www.journaldev.com/33588/install-maven-linux-ubuntu)

### **Dependency Installation on Mac**
* [Java Installation](https://docs.oracle.com/javase/8/docs/technotes/guides/install/mac_jdk.html)
* [Maven Installation](https://www.journaldev.com/2348/install-maven-mac-os)

### **Execution From Command Prompt**
```
java -jar CarParking_SquadStack-1.0-SNAPSHOT-shaded.jar { PATH_TO_INPUTFILE }
```

## **Sample Inputs & Output**
```
* Create_parking_lot 6 <br />
> Created parking of 6 slots

* Park KA-01-HH-1234 driver_age 21 <br />
> Car with vehicle registration number "KA-01-HH-1234" has been parked at slot number 1

* Park PB-01-HH-1234 driver_age 21 <br />
> Car with vehicle registration number "PB-01-HH-1234" has been parked at slot number 2

* Slot_numbers_for_driver_of_age 21 <br />
> 1,2

* Park PB-01-TG-2341 driver_age 40 <br />
> Car with vehicle registration number "PB-01-TG-2341" has been parked at slot number 3

* Slot_number_for_car_with_number PB-01-HH-1234 <br />
> 2

* Leave 2 <br />
> Slot number 2 vacated, the car with vehicle registration number "PB-01-HH-1234" left the space, 
the driver of the car was of age 21

* Park HR-29-TG-3098 driver_age 39 <br />
> Car with vehicle registration number "HR-29-TG-3098" has been parked at slot number 2

* Vehicle_registration_number_for_driver_of_age 18 <br />
> null
```