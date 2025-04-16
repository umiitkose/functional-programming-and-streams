package com.umiitkose.streams.example.terminal.foreach;

import java.util.Arrays;

public class Main {
    void main() {
        String[] countries = {"Türkiye", "Germany", "France", "Türkiye", "Italy", "Germany"};

        // forEach
        Arrays.stream(countries).forEach(System.out::println);
        // forEachOrdered
        //Arrays.stream(countries).forEachOrdered(System.out::println);
    }
}
