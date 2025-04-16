package com.umiitkose.streams.example.terminal.anymatch;

import java.util.List;

public class Main {
    void main() {
        List<String> countries = List.of("Türkiye", "Germany", "France", "Türkiye", "Italy", "Germany");
        boolean anyMatch = countries
                .stream()
                .anyMatch(s -> s.length() > 5); // true;

        System.out.println(anyMatch);

    }
}
