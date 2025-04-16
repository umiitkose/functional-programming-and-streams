package com.umiitkose.events.example.performace;

import java.util.stream.IntStream;

public class Performance1 {
    void main() {

        long l = System.currentTimeMillis();
        int toplam = 0;
        for (int i = 1; i <= 1_000_000; i++) {
            toplam += i;
        }
        long l1 = System.currentTimeMillis();
        System.out.println("For Performansı: " + (l1 - l) + " ms");


        long l2 = System.currentTimeMillis();
        long sum = IntStream.rangeClosed(1, 1_000_000)
                .parallel()
                .sum();
        long l3 = System.currentTimeMillis();
        System.out.println("Stream Performansı: " + (l3 - l2) + " ms");
    }
}
