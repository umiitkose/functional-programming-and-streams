package com.umiitkose.functional.programming.introduction;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Kısmi fonksiyon uygulaması, bir fonksiyonun bazı argümanlarını sabitleyerek daha az parametre alan yeni bir fonksiyon oluşturma işlemidir.
 */
public class PartialApplicationExample {
    public static void main(String[] args) {
        // İki parametre alan bir fonksiyon
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;

        // Kısmi uygulama ile ilk parametreyi 5 olarak sabitleme
        Function<Integer, Integer> addFive = b -> add.apply(5, b);

        System.out.println(addFive.apply(10)); // 5 + 10 = 15
        System.out.println(addFive.apply(3));  // 5 + 3 = 8

        // Genel bir kısmi uygulama yardımcı metodu
        BiFunction<String, Integer, String> repeat = (s, n) -> s.repeat(n);

        // İlk parametresi "Hello" olan kısmi uygulama
        Function<Integer, String> repeatHello = n -> repeat.apply("Hello", n);

        System.out.println(repeatHello.apply(3)); // "HelloHelloHello"
    }
}
