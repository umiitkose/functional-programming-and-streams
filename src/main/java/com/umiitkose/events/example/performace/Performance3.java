package com.umiitkose.events.example.performace;

import java.util.stream.LongStream;

public class Performance3 {
    void main() {
        int LIMIT = 1_000_000_000;

        // For Döngüsü
        long startFor = System.currentTimeMillis();
        long toplam = 0;
        for (long i = 1; i <= LIMIT; i++) {
            toplam += i * i;
        }
        long endFor = System.currentTimeMillis();
        System.out.println("For Performansı: " + (endFor - startFor) + " ms");

        // Stream (sequential)
        long startStream = System.currentTimeMillis();
        long streamSum = LongStream.rangeClosed(1, LIMIT)
                .map(x -> x * x)
                .sum();
        long endStream = System.currentTimeMillis();
        System.out.println("Stream Performansı: " + (endStream - startStream) + " ms");

        // Parallel Stream
        long startParallel = System.currentTimeMillis();
        long parallelSum = LongStream.rangeClosed(1, LIMIT)
                .parallel()
                .map(x -> x * x)
                .sum();
        long endParallel = System.currentTimeMillis();
        System.out.println("Parallel Stream Performansı: " + (endParallel - startParallel) + " ms");
    }

}
