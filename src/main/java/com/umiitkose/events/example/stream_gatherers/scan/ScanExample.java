package com.umiitkose.events.example.stream_gatherers.scan;

import java.util.List;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class ScanExample {
    void main() {
        List<String> list = Stream.of(1, 2, 3)
                .gather(Gatherers.scan(() -> "", (a, b) -> a + b))
                .toList();
        System.out.println(list);;
    }
}
