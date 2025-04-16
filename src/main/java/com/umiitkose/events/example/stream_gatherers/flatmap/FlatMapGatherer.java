package com.umiitkose.events.example.stream_gatherers.flatmap;

import java.util.function.Function;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class FlatMapGatherer {
    <E, R> Gatherer<E, ?, R> flatMapping(Function<E, Stream<R>> flatMapper) {
        Gatherer.Integrator<Void, E, R> integrator = (_, element, downstream) -> {
            flatMapper.apply(element).forEach(downstream::push);
            /**
             * There are at least two bugs in the following code: flatMapper.apply(mapped).forEach(downstream::push), plus a third one.
             *
             * The first bug is that a Downstream instance is not a thread-safe object. Calling forEach(downstream::push) is violating one of the rules of the Stream API: do not do any side effect from within Stream. If you do not follow this rule, you should be extra careful with parallel streams: they can create race conditions, which is exactly the case here. The solution is to protect yourself against a flatMapper that could return a parallel stream, and generate race conditions in the Downstream that is given to you. You can do so by just calling sequential on the returned stream: flatMapper.apply(mapped).sequential(). If the stream is already sequential, this call does not do anything, it returns this, and if it is a parallel stream, then it makes it non-parallel.
             *
             * The second bug has to do with the fact that a stream needs to be closed once used. In many cases calling Stream.close() does not do anything. But there are cases where a stream is opened on an I/O resource (like Files.lines()) where not calling it would lead to some resource leaking. Since a Stream implements AutoCloseable, you can open it in a try-with-resources statement. The pattern then becomes the following.
             * */
            return true;
        };
        return Gatherer.of(integrator);
    }

    void main() {
        Function<String, Stream<String>> flatMapper = s -> s.chars().mapToObj(Character::toString);
        Gatherer<String, ?, String> mappingGatherer = flatMapping(flatMapper);
        var result = Stream.of("one", "two", "three").gather(mappingGatherer).toList();
        System.out.println("result = " + result);
    }
}
