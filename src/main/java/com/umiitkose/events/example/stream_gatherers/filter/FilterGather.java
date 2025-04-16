package com.umiitkose.events.example.stream_gatherers.filter;

import java.util.function.Predicate;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class FilterGather {
    <T> Gatherer<T, ?, T> filter(Predicate<T> filter) {
        Gatherer.Integrator<Void, T, T> integrator = (_, value, downstream) -> {
            if (filter.test(value)) {
                return downstream.push(value);
            }
            return true;
        };
        return Gatherer.of(integrator);
    }

    void main() {
        Stream.of(1, 2, 3, 4, 5, 6)
                .gather(filter(i -> i % 2 == 0))
                .forEach(System.out::println);
    }
}
