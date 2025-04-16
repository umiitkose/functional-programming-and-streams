package com.umiitkose.streams.example.terminal.nonematch;

public class Main {
    void main() {
        // noneMatch
        String[] countries = {"Türkiye", "Germany", "France", "Türkiye", "Italy", "Germany"};
        boolean noneMatch = java.util.Arrays.stream(countries)
                .noneMatch(s -> s.length() > 7);
        System.out.println(noneMatch);

    }
}
