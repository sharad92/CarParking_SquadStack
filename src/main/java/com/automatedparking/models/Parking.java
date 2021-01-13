package com.automatedparking.models;

import org.dizitart.no2.IndexType;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

import java.io.Serializable;

@Indices({
        @Index(value = "age", type = IndexType.NonUnique),
        @Index(value = "registration", type = IndexType.Unique)
})
public class Parking implements Serializable {

    @Id
    private int slot;
    private int age;
    private String registration;

    public Parking() {

    }

    public Parking(int age, String registration) {
        setAge(age);
        setRegistration(registration);
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }
}
