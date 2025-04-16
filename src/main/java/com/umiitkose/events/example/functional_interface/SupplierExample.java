package com.umiitkose.events.example.functional_interface;

import java.util.function.Supplier;

public class SupplierExample {
    void main() {
        Supplier<Double> supplierExample = Math::random; //Method References
        supplierExample.get();

    }

}
