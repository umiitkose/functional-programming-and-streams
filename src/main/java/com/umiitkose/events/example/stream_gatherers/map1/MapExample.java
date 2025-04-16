package com.umiitkose.events.example.stream_gatherers.map1;

import java.util.function.Function;
import java.util.stream.Gatherer;
import java.util.stream.Stream;

public class MapExample {

    <T, R> Gatherer<T, ?, R> map(Function<T, R> function) {
        Gatherer.Integrator<Void, T, R> integrator = (state, element, downstream) -> {
            return downstream.push(function.apply(element));
        };
        return Gatherer.of(integrator);
    }

    void main(){
        Stream.of(1,2,3,4,5)
                .gather(map(i-> i*i))
                .forEach(System.out::println);
    }
}
