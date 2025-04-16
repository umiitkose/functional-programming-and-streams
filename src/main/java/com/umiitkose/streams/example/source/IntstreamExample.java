package com.umiitkose.streams.example.source;

import java.util.Random;

public class IntstreamExample {
    public static void main(String[] args) {
        Random random = new Random();

        random.ints(10,1,100)
                .forEach(System.out::println);
    }
}
