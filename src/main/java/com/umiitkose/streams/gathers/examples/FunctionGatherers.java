package com.umiitkose.streams.gathers.examples;

import java.util.function.Function;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class FunctionGatherers {

    <E, R> Gatherer<E, ?, R> mapping(Function<E, R> mapper) {
        Gatherer.Integrator<Void, E, R> integrator =
                (_, element, downstream) -> downstream.push(mapper.apply(element));
        Gatherer<E, ?, R> gatherer = Gatherer.of(integrator);
        return gatherer;
    }


    void main() {

        //Function<String, String> mapper = String::toUpperCase;

        Function<String, Integer> mapper = String::length;

        var mappingGatherer = mapping(mapper);
        var result = Stream.of("one", "two", "three")
                .gather(mappingGatherer)
                .toList();

        System.out.println("result = " + result);
    }
}
