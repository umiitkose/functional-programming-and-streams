package com.umiitkose.events.example.stream_gatherers.greedyvsof;

import java.util.HashSet;
import java.util.function.Supplier;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class Main {
    /*
    You have two factory methods on the Integrator interface.

Integrator.of() that takes a plain Gatherer.Integrator as a parameter.
Integrator.ofGreedy() that takes a Gatherer.Integrator.Greedy as a parameter.
There are two things that you need to keep in mind:

Gatherer.Integrator.Greedy is an extension of Gatherer.Integrator.
Both interfaces can be implemented with the same lambda expressions.

Defining an integrator as greedy means that this integrator never chooses to interrupt a stream on its own. It always transmits the rejecting state of the downstream it pushes elements to. So internally, the Gatherer API can take that into account and can activate some optimizations to execute your gatherer. If you do not follow the contract of a greedy integrator your code may fail. If you have a plain integrator that has a greedy behavior, your code will not fail, but you may miss some optimization opportunities.

So in a first step, you could choose to ignore this greedy behavior, and stick to plain integrators. Once you have set up your data processing pipeline you can then revisit each step and make the corresponding integrators greedy.


    *  */

    <E> Gatherer<E, ?, E> distinct() {
        Supplier<HashSet<E>> initializer = HashSet::new;

        Gatherer.Integrator.Greedy<HashSet<E>, E, E> integrator = (set, element, downstream) -> {
            if (set.add(element)) {
                return downstream.push(element);
            } else {
                return true;
            }
        };
        return Gatherer.ofSequential(initializer, integrator);
    }

    void main() {
        var result = Stream.of(1, 2, 3, 4, 5, 4, 3, 2, 1).gather(distinct()).toList();
        System.out.println("result = " + result);
    }
}
