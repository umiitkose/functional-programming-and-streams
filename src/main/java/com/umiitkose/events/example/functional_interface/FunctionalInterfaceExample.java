package com.umiitkose.events.example.functional_interface;

@FunctionalInterface
public interface FunctionalInterfaceExample {
    // Abstract method
    void execute();

    // Default method
    default void defaultMethod() {
        System.out.println("Default method in FunctionalInterfaceExample");
    }

    // Static method
    static void staticMethod() {
        System.out.println("Static method in FunctionalInterfaceExample");
    }
}


