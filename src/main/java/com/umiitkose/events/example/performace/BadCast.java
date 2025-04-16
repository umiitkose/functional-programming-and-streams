package com.umiitkose.events.example.performace;

import java.util.IntSummaryStatistics;
import java.util.Random;
import java.util.function.IntUnaryOperator;

public class BadCast {
    void main() {
        var elementsCount = 100_000_000;
        IntUnaryOperator multiplyByTwo = in -> in * 2;
        var rnd = new Random();
// FOR-LOOP
        long l = System.currentTimeMillis();
        var loopStats = new IntSummaryStatistics();
        for (int idx = 0; idx < elementsCount; idx++) {
            var value = rnd.nextInt();
            var subResult = multiplyByTwo.applyAsInt(value);
            var finalResult = multiplyByTwo.applyAsInt(subResult);
            loopStats.accept(finalResult);
        }
        long l2 = System.currentTimeMillis();
        System.out.println("FOR-LOOP Performansı: " + (l2 - l) + " ms");
// SEQUENTIAL IntStream
        long l3 = System.currentTimeMillis();
        var seqStats = rnd.ints(elementsCount)
                .map(multiplyByTwo)
                .map(multiplyByTwo)
                .summaryStatistics();
        long l4 = System.currentTimeMillis();
        System.out.println("SEQUENTIAL IntStream Performansı: " + (l4 - l3) + " ms");
// PARALLEL IntStream
        long l5 = System.currentTimeMillis();

        var parallelStats = rnd.ints(elementsCount) //ThreadLocalRandom
                .parallel()
                .map(multiplyByTwo)
                .map(multiplyByTwo)
                .summaryStatistics();
        long l6 = System.currentTimeMillis();
        System.out.println("PARALLEL IntStream Performansı: " + (l6 - l5) + " ms");
    }
}
