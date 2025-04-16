package com.umiitkose.events.example.stream_gatherers.map;

import java.util.function.Function;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class MapGather {

    <E, R> Gatherer<E, ?, R> mapping(Function<E, R> mapper) {
        Gatherer.Integrator<Void, E, R> integrator =
                (_, value, downstream) -> {
                    return downstream.push(mapper.apply(value));
                };
        return Gatherer.of(integrator);
    }

    void main() {
        Stream<Integer> gather = Stream.of(1, 2, 3, 4, 5, 6)
                .gather(mapping(i -> i * 2));

        gather.forEach(System.out::println);
    }

}
