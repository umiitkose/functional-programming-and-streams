package com.umiitkose.streams.gathers.deneme;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class GathererImp implements Gatherer<Integer, List<String>, List<String>> {
    @Override
    public Supplier<List<String>> initializer() {
        return ArrayList::new;
    }

    @Override
    public Integrator<List<String>, Integer, List<String>> integrator() {
        return Integrator.of((list, value, downstream) -> {
            list.add(String.valueOf(value));
            return downstream.push(list);
        });

    }

    @Override
    public BinaryOperator<List<String>> combiner() {
        return Gatherer.super.combiner();
    }

    @Override
    public BiConsumer<List<String>, Downstream<? super List<String>>> finisher() {
        return Gatherer.super.finisher();
    }

    @Override
    public <RR> Gatherer<Integer, ?, RR> andThen(Gatherer<? super List<String>, ?, ? extends RR> that) {
        return Gatherer.super.andThen(that);
    }


    public static void main(String[] args) {
        Gatherer<Integer, List<String>, List<String>> gatherer = new GathererImp();
        Stream.of(1, 2, 3, 4, 5)
                .gather(gatherer)
                .forEach(System.out::println);

    }

}
