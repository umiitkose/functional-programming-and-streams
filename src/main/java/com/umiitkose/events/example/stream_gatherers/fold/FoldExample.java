package com.umiitkose.events.example.stream_gatherers.fold;

import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class FoldExample {
    void main() {
        Stream.of(1, 2, 3, 4, 5, 6, 7)
                .gather(Gatherers.fold(() -> 0, (a, b) -> a + b))
                .findFirst().ifPresent(System.out::println);
    }

}
