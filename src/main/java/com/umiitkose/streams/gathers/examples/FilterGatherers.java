package com.umiitkose.streams.gathers.examples;

import java.util.function.Predicate;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class FilterGatherers {
    <E> Gatherer<E, ?, E> filtering(Predicate<E> filter) {
        Gatherer.Integrator<Void, E, E> integrator =
                (_, element, downstream) -> {
                    if (filter.test(element)) {
                        return downstream.push(element);
                    } else {
                        return true;
                    }
                };
        Gatherer<E, ?, E> gatherer = Gatherer.of(integrator);
        return gatherer;
    }

    void main() {
        Predicate<String> filter = s -> s.length() == 3;
        var filteringGatherer = filtering(filter);
        var result = Stream.of("one", "two", "three")
                .gather(filteringGatherer)
                .toList();
        System.out.println("result = " + result);
    }
}
