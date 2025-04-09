package com.umiitkose.functional.programming.java_functional_interfaces;

import java.util.function.Supplier;

public class SupplierExample {
    void main() {
        Supplier<Double> supplierExample = Math::random; //Method References
        supplierExample.get();

    }

}
