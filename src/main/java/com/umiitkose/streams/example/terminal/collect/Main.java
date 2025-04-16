package com.umiitkose.streams.example.terminal.collect;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    void main() {
        List<String> countries = List.of("Türkiye", "Germany", "France", "Türkiye", "Italy", "Germany");
        List<String> collector = countries
                .stream()
                .limit(2)
                .collect(Collectors.toList()); // true;

        System.out.println(collector);
    }
}
