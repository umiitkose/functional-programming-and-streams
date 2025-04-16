package com.umiitkose.events.example.stream_gatherers.limit;

import java.util.function.Supplier;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class LimitExample {
    <E> Gatherer<E, ?, E> limit(long limit) {
        class Box {
            long counter = 0L;
        }
        Supplier<Box> initializer = Box::new;
        Gatherer.Integrator<Box, E, E> integrator =
                (box, element, downstream) -> {
                    if (box.counter < limit) {
                        box.counter++;
                        return downstream.push(element);
                    } else {
                        return false;
                    }
                };
        return Gatherer.ofSequential(initializer, integrator);
    }

    void main() {
        Stream.of(1, 2, 3, 4, 5, 6, 7)
                .gather(limit(3))
                .forEach(System.out::println);
    }

}
    /*  if (downstream.isRejecting()) {
                        return false;
                        /* Bu şekilde yazmanıza gerek yoktur.
                        * A new downstream always starts in the non-rejecting state.
                        * A downstream can only switch this state from non-rejecting to rejecting. So a rejecting downstream stays in that state.
                        * A downstream can only switch this state when you push an element to it.

                    } else*/
