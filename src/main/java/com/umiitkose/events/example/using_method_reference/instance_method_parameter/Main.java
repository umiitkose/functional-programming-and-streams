package com.umiitkose.events.example.using_method_reference.instance_method_parameter;

public class Main {
    public static void main(String[] args) {

        StringParameterChecker methodRef = String::isEmpty;
        StringParameterChecker methodRef2 = (String s) -> s.isEmpty();
    }
}
