package com.umiitkose.events.example.using_method_reference.static_method_reference;

public class Main {
    public static void main(String[] args) {
        Converter converter = Math::round;
        Converter converter1 = (x) -> Math.round(x);
        System.out.println(converter.round(100.52));
    }
}
