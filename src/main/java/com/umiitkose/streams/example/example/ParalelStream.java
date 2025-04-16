package com.umiitkose.streams.example.example;

import java.util.stream.LongStream;

public class ParalelStream {
    void main() {
        System.out.println("ParalelStream");
        // Parallel Stream
        long t1 = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(1, 10_000_000)
                .parallel()
                .sum();
        System.out.println(sum);
        long t2 = System.currentTimeMillis();
        System.out.println("Parallel Stream Time: " + (t2 - t1) + " ms");

        // Sequential Stream

        long t3 = System.currentTimeMillis();
        long sum2 = LongStream.rangeClosed(1, 10_000_000)
                .sequential()
                .sum();
        System.out.println(sum2);
        long t4 = System.currentTimeMillis();
        System.out.println("Sequential Stream Time: " + (t4 - t3) + " ms");
    }
}
