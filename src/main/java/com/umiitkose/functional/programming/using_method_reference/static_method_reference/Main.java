package com.umiitkose.functional.programming.using_method_reference.static_method_reference;

public class Main {
    public static void main(String[] args) {
        Converter converter = Math::round;
        System.out.println(converter.round(100.52));
    }
}
