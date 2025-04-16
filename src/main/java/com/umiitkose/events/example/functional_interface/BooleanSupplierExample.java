package com.umiitkose.events.example.functional_interface;

import java.util.function.BooleanSupplier;

public class BooleanSupplierExample {
    void main() {
        BooleanSupplier booleanSupplier = () -> true;
        boolean asBoolean = booleanSupplier.getAsBoolean();
    }
}
