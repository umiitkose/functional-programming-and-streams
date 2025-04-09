package com.umiitkose.streams.example.intermediate.distinct;

import java.util.List;

public class Main {
    void main() {
        List<String> countries = List.of("Türkiye", "Germany", "France", "Türkiye", "Italy", "Germany");
        List<String> distinctCountries = countries
                .stream()
                .distinct()
                .toList();

        System.out.println(distinctCountries);

    }
}
