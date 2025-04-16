package com.umiitkose.events.example.functional_interface;

import java.util.function.IntFunction;

public class IntFunctionExample {
    void main() {
        IntFunction<Integer> integerIntFunction= (x) -> x * 2; //take primitive parameter return object
        Integer apply = integerIntFunction.apply(5);
        System.out.println(apply);
    }
}
