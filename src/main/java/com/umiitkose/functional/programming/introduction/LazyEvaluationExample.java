package com.umiitkose.functional.programming.introduction;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class LazyEvaluationExample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Stream işlemleri tembel olarak değerlendirilir
        Stream<Integer> stream = numbers.stream()
                .filter(n -> {
                    System.out.println("Filtering: " + n);
                    return n % 2 == 0;
                })
                .map(n -> {
                    System.out.println("Mapping: " + n);
                    return n * n;
                });

        // Şu ana kadar hiçbir işlem gerçekleştirilmedi

        // Terminal işlemi çağrıldığında değerlendirme başlar
        System.out.println("Stream değerlendiriliyor...");
        stream.forEach(n -> System.out.println("Result: " + n));

    }
}
