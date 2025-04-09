package com.umiitkose.functional.programming.introduction;

import java.util.function.Function;

/**
 * Currying, birden çok parametre alan bir fonksiyonu, her biri tek parametre alan fonksiyonların zincirine dönüştürme işlemidir.
 */
public class CurryingExample {
    public static void main(String[] args) {
        // Normal fonksiyon: (a, b, c) -> a + b + c

        // Curried fonksiyon: a -> (b -> (c -> a + b + c))
        Function<Integer, Function<Integer, Function<Integer, Integer>>> curriedAdd =
                a -> b -> c -> a + b + c;

        // Curried fonksiyonu kullanma
        int result = curriedAdd
                .apply(1)  // a = 1, returns a function expecting b
                .apply(2)  // b = 2, returns a function expecting c
                .apply(3); // c = 3, returns 1 + 2 + 3 = 6

        System.out.println(result); // 6
    }
}
