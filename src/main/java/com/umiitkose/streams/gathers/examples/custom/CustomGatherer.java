package com.umiitkose.streams.gathers.examples.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

public class CustomGatherer {
    public static Gatherer<Log, List<Log>, String> of(final int threshold) {
        return Gatherer.of(initializer(), integrator(threshold), combiner(), finisher(threshold));
    }

    public static Supplier<List<Log>> initializer() {
        return ArrayList::new;
    }

    public static Gatherer.Integrator<List<Log>, Log, String> integrator(final int threshold) {
        return ((internalState, element, downstream) -> {
            if (downstream.isRejecting()) {
                return false;
            }

            if (element.level().equals(Log.Level.ERROR)) {
                internalState.add(element);
            } else {

                if (internalState.size() >= threshold) {
                    internalState.stream().map(Log::details).forEach(downstream::push);
                }

                internalState.clear();
            }

            return true;
        });
    }

    public static BinaryOperator<List<Log>> combiner() {
        return (_, _) -> {
            throw new UnsupportedOperationException("Cannot be parallelized");
        };
    }

    static BiConsumer<List<Log>, Gatherer.Downstream<? super String>> finisher(final int threshold) {
        return (state, downstream) -> {
            if (!downstream.isRejecting() && state.size() >= threshold) {
                state.stream().map(Log::details).forEach(downstream::push);
            }
        };
    }
}
