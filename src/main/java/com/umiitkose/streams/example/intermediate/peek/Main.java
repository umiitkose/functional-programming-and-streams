package com.umiitkose.streams.example.intermediate.peek;

import java.util.List;

public class Main {
    void main() {
        List<Integer> countries = List.of(1, 2, 3, 4, 2, -1, 4, 6, 7, 4);
        List<Integer> distinctCountries = countries
                .stream()
                .dropWhile(s -> s > 0)
                .peek(s -> System.out.println(s + ": " + s))
                .toList();

        System.out.println(distinctCountries);

    }
}
