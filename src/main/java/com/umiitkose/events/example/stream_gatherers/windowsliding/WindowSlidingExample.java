package com.umiitkose.events.example.stream_gatherers.windowsliding;

import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class WindowSlidingExample {
    void main() {
        Stream.of(1, 2, 3, 4, 5, 6)
                .gather(Gatherers.windowSliding(3))
                .forEach(i -> System.out.print(i + " "));

    }
}
