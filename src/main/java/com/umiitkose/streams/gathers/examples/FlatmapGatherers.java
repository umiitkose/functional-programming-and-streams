package com.umiitkose.streams.gathers.examples;

import java.util.function.Function;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class FlatmapGatherers {
    <E, R> Gatherer<E, ?, R> flatMapping(Function<E, Stream<R>> flatMapper) {
        Gatherer.Integrator<Void, E, R> integrator =
                (_, element, downstream) -> {
                    flatMapper.apply(element).forEach(downstream::push);
                    return true;
                };
        Gatherer<E, ?, R> gatherer = Gatherer.of(integrator);
        return gatherer;
    }

    void main() {
        Function<String, Stream<String>> flatMapper =
                s -> s.chars().mapToObj(Character::toString);
        Gatherer<String, ?, String> mappingGatherer = flatMapping(flatMapper);
        var result = Stream.of("one", "two", "three")
                .gather(mappingGatherer)
                .toList();
        System.out.println("result = " + result);
    }
}
