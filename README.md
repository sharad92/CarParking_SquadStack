## **Automated Car Parking**
This is an implementation of a parking structure with an automated ticketing system that provides interaction free 
parking facility. 

### **Problem Statement && Details**
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

### **Getting Started**