package com.umiitkose.functional.programming.immutability.record;

public class Main {
    void main() {
        RecordFeatures<String> recordFeatures = new RecordFeatures<>("John Doe", 25, "Some Value");
        System.out.println("Name: " + recordFeatures.name());
        System.out.println("Age: " + recordFeatures.age());
        System.out.println("Value: " + recordFeatures.value());
        System.out.println("isAdult: " + recordFeatures.isAdult());

    }
}
