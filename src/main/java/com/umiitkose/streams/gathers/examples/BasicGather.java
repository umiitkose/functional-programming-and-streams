package com.umiitkose.streams.gathers.examples;

import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class BasicGather {
    void main() {
        Gatherer.Integrator<Void, String, String> integrator =
                (_, element, downstream) -> downstream.push(element);
        Gatherer<String, ?, String> identityGatherer =
                Gatherer.of(integrator);

        var result = Stream.of("one", "two", "three")
                .gather(identityGatherer)
                .toList();
        System.out.println("result = " + result);

    }
}
