package com.umiitkose.streams.example.terminal.toArray;

import java.util.stream.Stream;

public class Main {
    void main() {
        // toArray
        String[] array = Stream.of("turkiye", "java", "community")
                .toArray(String[]::new);// [turkiye, java, community]
    }
}
