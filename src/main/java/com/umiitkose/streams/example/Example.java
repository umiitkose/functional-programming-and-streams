package com.umiitkose.streams.example;

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Example {
    void main(){
        long numberOfWords =
                Stream.of("the", "", "fox", "jumps", "over", "the", "", "dog")  // (1)
                        .filter(Predicate.not(String::isEmpty))                   // (2)
                        .collect(Collectors.counting());
        System.out.println(numberOfWords);


    }
}
