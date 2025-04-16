package com.umiitkose.events.example.stream_gatherers.finisher;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class Main {

    /**
     * There are cases where your gatherer needs to wait for all the elements of its upstream to start pushing elements to its downstream. This is the case if you need to create a gatherer that sorts the elements of your upstream. You cannot start producing elements until you have seen them all.
     * <p>
     * Such a gatherer needs to add all the elements it sees to an internal buffer. Then, when there is no more element pushed to its integrator, push these elements to the downstream. So far you have not seen any solution to do that. Nothing is telling your gatherer that no more elements are coming from the upstream. Actually, the upstream pushes elements to the integrator of your gatherer, and when there is no more element, nothing happens.
     * <p>
     * This is why the Gatherer API gives you another element, called a finisher, that is called by the implementation when no more elements are to be pushed to your integrator.
     * <p>
     * This finisher can be seen as a simplified integrator.
     * <p>
     * It takes the current state of your gatherer, if you defined one by providing an initializer.
     * It does not take the current element of the stream, just because when it is called, there is no more element, precisely.
     * It takes the downstream so that you can push elements to it.
     * It does not return anything, since after this finisher is called, nothing can happen anyway.
     * So a finisher takes two parameters: your state and the downstream, and does not return anything. It is modeled by a BiConsumer<State, Downstream>.
     */
    <E, R> Gatherer<E, ?, R> mapThenFinish(Function<E, R> mapper, Supplier<R> supplier) {
        Gatherer.Integrator<Void, E, R> integrator =
                (_, element, downstream) -> downstream.push(mapper.apply(element));
        BiConsumer<Void, Gatherer.Downstream<? super R>> finisher =
                (_, downstream) -> downstream.push(supplier.get());
        Gatherer<E, ?, R> gatherer = Gatherer.of(
                integrator, finisher);
        return gatherer;
    }

    void main() {
        Gatherer<String, ?, String> gatherer = mapThenFinish(String::toUpperCase, () -> "DONE");
        var result = Stream.of("one", "two", "three")
                .gather(gatherer)
                .toList();
        System.out.println("result = " + result);
    }
}

