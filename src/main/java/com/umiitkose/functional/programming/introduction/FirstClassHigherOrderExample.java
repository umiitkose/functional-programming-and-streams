package com.umiitkose.functional.programming.introduction;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FirstClassHigherOrderExample {
    public static void main(String[] args) {
        // Birinci sınıf fonksiyon (lambda ifadesi)
        Function<Integer, Integer> square = x -> x * x;

        // Yüksek dereceli fonksiyon (başka bir fonksiyonu parametre olarak alan)
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // map() yüksek dereceli bir fonksiyondur
        List<Integer> squares = numbers.stream()
                .map(square)
                .toList();

        System.out.println(squares); // [1, 4, 9, 16, 25]

        // Fonksiyon döndüren yüksek dereceli fonksiyon
        Function<Integer, Function<Integer, Integer>> multiplier =
                a -> b -> a * b;

        Function<Integer, Integer> timesFive = multiplier.apply(5);
        System.out.println(timesFive.apply(3)); // 15
    }
}
