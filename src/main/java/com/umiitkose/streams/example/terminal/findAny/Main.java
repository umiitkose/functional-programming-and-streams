package com.umiitkose.streams.example.terminal.findAny;

import java.util.List;
import java.util.Optional;

public class Main {
    void main() {
        List<String> countries = List.of("Türkiye", "Germany", "France", "Türkiye", "Italy", "Germany");
        Optional<String> findAny = countries
                .stream()
                .filter(s -> s.length() > 4)
                .findAny();

        System.out.println(findAny.orElse("No country found"));
    }
}
