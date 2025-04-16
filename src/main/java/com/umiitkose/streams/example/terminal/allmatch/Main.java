package com.umiitkose.streams.example.terminal.allmatch;

import java.util.List;

public class Main {
    void main() {
        List<String> countries = List.of("Türkiye", "Germany", "France", "Türkiye", "Italy", "Germany");
        boolean allMatch = countries
                .stream()
                .allMatch(s -> s.length() > 5); // true;

        System.out.println(allMatch);
    }
}
