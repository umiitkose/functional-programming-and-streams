package com.umiitkose.events.example.performace;

import java.util.stream.LongStream;

public class Performance2 {
    void main() {

        long l = System.currentTimeMillis();
        long toplam = 0;
        for (int i = 1; i <= 1_000_000_000; i++) {

            toplam += (long) i * i;
        }
        long l1 = System.currentTimeMillis();
        System.out.println("For Performansı: " + (l1 - l) + " ms");


        long l2 = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(1, 1_000_000_000)
                .parallel()
                .map(x -> x * x) // işlem süresi artıyor
                .sum();
        long l3 = System.currentTimeMillis();
        System.out.println("Stream Performansı: " + (l3 - l2) + " ms");
    }
}
