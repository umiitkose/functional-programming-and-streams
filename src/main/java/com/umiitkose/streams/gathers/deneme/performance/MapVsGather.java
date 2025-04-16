package com.umiitkose.streams.gathers.deneme.performance;

import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class MapVsGather {

    void main() {
        var uppercaseGatherer = Gatherer.<String, String>of((state, element, downstream)
                -> downstream.push(element.toUpperCase()));

        long l = System.currentTimeMillis();
        Stream.of("a", "b", "c", "d", "e", "f", "g", "a", "b", "c", "d", "e", "f", "g")
                .map(String::toUpperCase)
                .forEach(System.out::print);
        long l2 = System.currentTimeMillis();
        System.out.println("map -> " + (l2 - l) + "ms");

        long l3 = System.currentTimeMillis();
        Stream.of("a", "b", "c", "d", "e", "f", "g", "a", "b", "c", "d", "e", "f", "g")
                .gather(uppercaseGatherer)
                .forEach(System.out::print);
        long l4 = System.currentTimeMillis();
        System.out.println("gather -> " + (l4 - l3) + "ms");


    }
}
