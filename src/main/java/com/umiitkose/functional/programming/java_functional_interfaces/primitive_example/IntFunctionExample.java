package com.umiitkose.functional.programming.java_functional_interfaces.primitive_example;

import java.util.function.IntFunction;

public class IntFunctionExample {
    void main() {
        IntFunction<Integer> integerIntFunction= (x) -> x * 2; //take primitive parameter return object
        Integer apply = integerIntFunction.apply(5);

    }
}
