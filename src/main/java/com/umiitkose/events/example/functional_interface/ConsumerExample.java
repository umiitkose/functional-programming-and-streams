package com.umiitkose.events.example.functional_interface;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ConsumerExample {
    void main() {
        Consumer<String> stringConsumer = System.out::println; //Method References

        stringConsumer.accept("Hello");

        BiConsumer<String, Integer> stringBiConsumer = (String a, Integer b) -> System.out.format("Ben %s,Yaşım %d", a, b);
        stringBiConsumer.accept("Ümit", 32);


    }
}
