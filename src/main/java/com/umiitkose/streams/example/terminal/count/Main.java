package com.umiitkose.streams.example.terminal.count;

import java.util.List;

public class Main {
    void main() {
        List<String> countries = List.of("Türkiye", "Germany", "France", "Türkiye", "Italy", "Germany");
        long allMatch = countries
                .stream()
                .filter(s -> s.length() > 6) // true;
                .count();

        System.out.println(allMatch);
    }
}
