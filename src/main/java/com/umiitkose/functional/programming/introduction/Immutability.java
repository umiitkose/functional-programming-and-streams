package com.umiitkose.functional.programming.introduction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Immutability {

    public static void main(String[] args) {
        // Değişmez (immutable) sınıf kullanımı
        final String text = "Hello";
        // String değişmezdir, text.toUpperCase() yeni bir nesne döndürür
        String upperText = text.toUpperCase();
        System.out.println(text);       // Hala "Hello"
        System.out.println(upperText);  // "HELLO"

        // Koleksiyonları değişmez yapmak
        List<String> mutableList = new ArrayList<>();
        mutableList.add("one");
        mutableList.add("two");

        // Değişmez liste oluşturma
        List<String> immutableList = Collections.unmodifiableList(mutableList);
        List<String> listOfImmutable = List.of("one", "two");

        // immutableList.add("three"); // UnsupportedOperationException fırlatır
        //listOfImmutable.add("three"); // UnsupportedOperationException fırlatır
    }
}
