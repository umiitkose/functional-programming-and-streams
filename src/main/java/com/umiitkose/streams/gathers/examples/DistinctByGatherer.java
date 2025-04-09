package com.umiitkose.streams.gathers.examples;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Gatherer;

public class DistinctByGatherer<T, P> implements Gatherer<T, Set<P>, T> {

    private final Function<T,P> selector;

    public DistinctByGatherer(Function<T, P> selector) {
        this.selector = selector;
    }


    @Override
    public Supplier<Set<P>> initializer() {
        return HashSet::new;
    }

    @Override
    public Integrator<Set<P>, T, T> integrator() {
        return Integrator.ofGreedy((state, item, downstream) -> {
            P extracted = selector.apply(item);
            if(!state.contains(extracted)) {
                state.add(extracted);
                downstream.push(item);
            }

            return true;
        });
    }
}