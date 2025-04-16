package com.umiitkose.streams.gathers.deneme;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class GathererMain {
    void main() {
        Gatherer.Integrator<Void, Integer, Integer> integrator = (Void, element, downstream) -> {
            System.out.println("Element: " + element);
            if (element < 3) {
                return downstream.push(element);
            }
            return downstream.isRejecting();
        };

        Gatherer<Integer, ?, Integer> gatherer = Gatherer.of(integrator);

        var result = Stream.of(1, 2, 3, 4, 5)
                .gather(gatherer)
                .toList();

        System.out.println(result);

        Gatherer<Integer, Integer, Integer> sum = new Gatherer<>() {

            @Override
            public Supplier<Integer> initializer() {
                return () -> {
                    System.out.println("Initializer");
                    return 0;
                };
            }

            @Override
            public Integrator<Integer, Integer, Integer> integrator() {
                return Integrator.of((state, element, downstream) -> {
                    System.out.println("Integrator");
                    state += element;
                    return downstream.push(state);
                });
            }

            @Override
            public BinaryOperator<Integer> combiner() {
                return Gatherer.super.combiner();
            }

            @Override
            public BiConsumer<Integer, Downstream<? super Integer>> finisher() {
                return (state, downstream) -> {
                    System.out.println("Finisher");
                };

            }

        };

        var result1 = Stream.of(1, 2, 3, 4, 5)
                .gather(sum)
                .toList();
        System.out.println(result1);

    }
}
