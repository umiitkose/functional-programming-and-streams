package com.umiitkose.events.example.stream_gatherers.fusing;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class FusingExample {
    <E, R, RR> Gatherer<E, ?, RR> fusing(Function<E, R> mapper,
                                         Predicate<R> filter,
                                         Function<R, Stream<RR>> flatMapper) {
        Gatherer.Integrator<Void, E, RR> integrator =
                (_, element, downstream) -> {
                    R mapped = mapper.apply(element);
                    if (filter.test(mapped)) {
                        try (var flatMapped = flatMapper.apply(mapped)) {
                            flatMapped.sequential().forEach(downstream::push);
                        }
                    }
                    return true;
                };
        return Gatherer.of(integrator);
    }

    void main() {
        Function<String, String> mapper = String::toUpperCase;
        Predicate<String> filter = s -> s.length() == 3;
        Function<String, Stream<String>> flatMapper =
                s -> s.chars().mapToObj(Character::toString);
        Gatherer<String, ?, String> fusedGatherer = fusing(mapper, filter, flatMapper);

        var result = Stream.of("one", "two", "three")
                .gather(fusedGatherer)
                .toList();
        System.out.println("result = " + result);
    }


}
