package com.umiitkose.events.example.stream_gatherers.distinct;

import java.util.HashSet;
import java.util.function.Supplier;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class DistinctExample {
    <E> Gatherer<E, ?, E> distinct() {
        Supplier<HashSet<E>> initializer = HashSet::new;

        Gatherer.Integrator<HashSet<E>, E, E> integrator =
                (set, element, downstream) -> {
                    if (set.add(element)) {
                        return downstream.push(element);
                    } else {
                        return true;
                    }
                };
        return Gatherer.ofSequential(initializer, integrator);
    }

    void main() {
        var result = Stream.of(1, 2, 3, 4, 5, 4, 3, 2, 1)
                .gather(distinct())
                .toList();
        System.out.println("result = " + result);
    }
}