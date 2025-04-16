package com.umiitkose.events.example.functional_interface;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class PredicateExample {
    void main() {
        Predicate<String> stringPredicate = String::isEmpty; //Method References

        stringPredicate.test("Hello");

        BiPredicate<String, String> stringBiPredicate = (String a, String b) -> test(a, b);

        stringBiPredicate.test("Ümit", "");

    }

    public boolean test(String a, String b) {
        return a.startsWith("B") || b.endsWith("t");
    }
}
