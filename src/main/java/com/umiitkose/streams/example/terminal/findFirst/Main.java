package com.umiitkose.streams.example.terminal.findFirst;

import java.util.stream.Stream;

public class Main {
    void main() {

        String firstCountry = Stream.of("Türkiye", "Germany", "France", "Türkiye", "Italy", "Germany")
                .findFirst()
                .orElse("No country found"); // true;

        System.out.println(firstCountry);
    }
}
