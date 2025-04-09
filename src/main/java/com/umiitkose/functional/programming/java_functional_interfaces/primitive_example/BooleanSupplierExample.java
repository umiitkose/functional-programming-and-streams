package com.umiitkose.functional.programming.java_functional_interfaces.primitive_example;

import java.util.function.BooleanSupplier;

public class BooleanSupplierExample {
    void main() {
        BooleanSupplier booleanSupplier = () -> true;
        boolean asBoolean = booleanSupplier.getAsBoolean();
    }
}
