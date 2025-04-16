package com.umiitkose.streams.example.example;

import java.util.stream.IntStream;

public class ForAndIterateStream {
    void main() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }

        //Java 8 - Stream api - for example
        IntStream.iterate(0, i -> i + 1)
                .limit(5)
                .forEach(System.out::println);

        //Java 9 - Stream api - for example
        IntStream.iterate(0, i -> i < 5, i -> i + 1)
                .forEach(System.out::println);

        // range
        IntStream.range(0, 5)
                .forEach(System.out::println);

        IntStream.rangeClosed(0, 4)
                .forEach(System.out::println);
    }
}
