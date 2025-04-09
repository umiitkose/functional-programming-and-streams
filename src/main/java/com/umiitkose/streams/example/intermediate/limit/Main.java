package com.umiitkose.streams.example.intermediate.limit;

import java.util.List;

public class Main {
    void main() {
        List<String> countries = List.of("Türkiye", "Germany", "France", "Türkiye", "Italy", "Germany");
        List<String> distinctCountries = countries
                .stream()
                .limit(2)
                .toList();

        System.out.println(distinctCountries);
    }
}
