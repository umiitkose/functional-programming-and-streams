package com.umiitkose.streams.example.example;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class InfiniteStream {
    void main(){
        Stream.iterate(0, n -> n + 1)
                .limit(10)
                .forEach(System.out::println);

        IntStream.generate(() -> new Random().nextInt(100))
                .limit(10)
                .forEach(System.out::println);


    }
}
