package com.umiitkose.events.example.using_method_reference.constructor_example;

import java.util.function.Supplier;

public class Main {
    void main() {
        Supplier<String> newString = String::new;
        Supplier<String> newString2 = () -> new String();


    }

}
