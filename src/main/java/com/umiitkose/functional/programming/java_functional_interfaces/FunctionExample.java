package com.umiitkose.functional.programming.java_functional_interfaces;

import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionExample {
    void main() {
        Function<String, Integer> integerFunction = String::length; //Method References

        integerFunction.apply("Hello");

        BiFunction<String, String, Boolean> stringBooleanBiFunction = (String a, String b) -> test(a, b);

        stringBooleanBiFunction.apply("Ümit", "");

    }

    public boolean test(String a, String b) {
        return a.startsWith("B") || b.endsWith("t");
    }
}
