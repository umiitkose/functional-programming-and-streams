package com.umiitkose.streams.example.terminal.reduce;

import java.util.stream.Stream;

public class Main {

    void main(){
        // reduce
        String[] countries = {"Türkiye", "Germany", "France", "Türkiye", "Italy", "Germany"};
        String result = java.util.Arrays.stream(countries)
                .reduce(" ", (s1, s2) -> s1 + s2);
        System.out.println(result);

        Integer reduce = Stream.of(1, 2, 3, 4, 5, 6, 7)
                .reduce(0, Integer::sum);
        System.out.println(reduce);
    }
}
