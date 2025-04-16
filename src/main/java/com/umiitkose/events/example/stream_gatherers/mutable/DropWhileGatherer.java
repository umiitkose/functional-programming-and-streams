package com.umiitkose.events.example.stream_gatherers.mutable;

import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class DropWhileGatherer {
    <E> Gatherer<E, ?, E> dropWhile(Predicate<E> predicate) {
        class Box {
            boolean open = false;
        }

        Supplier<Box> initializer = Box::new;

        Gatherer.Integrator<Box, E, E> integrator = (box, element, downstream) -> {
            if (!box.open && predicate.test(element)) {
                box.open = true;
            }
            if (box.open)
                downstream.push(element);
            return true;
        };
        return Gatherer.ofSequential(initializer, integrator); //of veya ofSequential paralel olmadığını söyler.


        /* Kısaltılmış (Anonymous class) hali*/
   /*
    <E > Gatherer < E, ?,E > dropWhile(Predicate < E > predicate) {
            Gatherer<E, ?, E> gatherer = Gatherer.ofSequential(
                    () -> new Object() {
                        boolean open = false;
                    },
                    (box, element, downstream) -> {
                        if (!box.open && predicate.test(element)) {
                            box.open = true;
                        }
                        if (box.open) {
                            downstream.push(element);
                        }
                        return true;
                    });
            return gatherer;
        }

        Predicate<Integer> predicate = x -> x > 3;
        var result = Stream.of(1, 2, 3, 4, 5, 4, 3, 2, 1)
                .gather(dropWhile(predicate))
                .toList();
        System.out.println("result = " + result);*/
    }

    void main() {
        Predicate<Integer> predicate = x -> x > 3;
        var result = Stream.of(1, 2, 3, 4, 5, 4, 3, 2, 1)
                .gather(dropWhile(predicate))
                .toList();
        System.out.println("result = " + result);
    }

}
