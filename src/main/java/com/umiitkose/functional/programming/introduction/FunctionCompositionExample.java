package com.umiitkose.functional.programming.introduction;

import java.util.function.Function;

/** Fonksiyonların birbirleriyle birlikte kullanımı */
public class FunctionCompositionExample {
    public static void main(String[] args) {
        Function<Integer, Integer> multiplyByTwo = x -> x * 2;
        Function<Integer, Integer> addTen = x -> x + 10;

        // compose: önce addTen, sonra multiplyByTwo uygular
        Function<Integer, Integer> composedFunction = multiplyByTwo.compose(addTen);

        // andThen: önce multiplyByTwo, sonra addTen uygular
        Function<Integer, Integer> chainedFunction = multiplyByTwo.andThen(addTen);

        System.out.println(composedFunction.apply(5)); // (5+10)*2 = 30
        System.out.println(chainedFunction.apply(5));  // (5*2)+10 = 20
    }
}
