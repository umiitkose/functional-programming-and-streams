package com.umiitkose.streams.example.intermediate.filter;

import java.util.List;

public class Main {
    void main() {
        List<String> countries = List.of("Türkiye", "Germany", "France", "Türkiye", "Italy", "Germany");
        List<String> distinctCountries = countries
                .stream()
                .filter(s -> s.startsWith("T"))
                .toList();

        System.out.println(distinctCountries);
    }
}
