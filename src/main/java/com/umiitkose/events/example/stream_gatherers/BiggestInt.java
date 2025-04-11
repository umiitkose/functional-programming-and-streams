package com.umiitkose.events.example.stream_gatherers;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public record BiggestInt(int limit) implements Gatherer<Integer, Integer, Integer> {

    @Override
    public Supplier<Integer> initializer() {
        System.out.println("integrator");
        return () -> Integer.MIN_VALUE;
    }

    @Override
    public Integrator<Integer, Integer, Integer> integrator() {
        return Integrator.of((max, element, downstream) -> {
            System.out.println("integrator");
            if (element >= limit) {
                downstream.push(element);
                return false;
            }
            return true;
        });
    }

    @Override
    public BinaryOperator<Integer> combiner() {
        return (leftMax, rightMax) -> {
            System.out.println("combiner: " + leftMax + ", " + rightMax);
            return leftMax;
        };
    }

    @Override
    public BiConsumer<Integer, Downstream<? super Integer>> finisher() {

        return (max, downstream) -> {
            System.out.println("finisher");
        };
    }

    public static void main(String[] args) {
        System.out.println(Stream.of(5, 4, 2, 1, 6, 12, 8, 9)
                .gather(new BiggestInt(11))
                .findFirst()
                .get());
    }
}
