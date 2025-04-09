package com.umiitkose.example;

import java.util.function.Consumer;

public class Example1 {
    void main() {
        var value = 5;

        value = 6;

        var finalValue = value;
        Consumer<Integer> valueConsumer = (x) -> System.out.println(x);

        valueConsumer.accept(value);

        EmptyConsumer emptyConsumer = () -> System.out.println(finalValue);
    }
}
