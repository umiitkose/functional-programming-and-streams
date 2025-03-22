package com.umiitkose.functional.programming.using_method_reference.instance_method_parameter;

public class Main {
    public static void main(String[] args) {
        StringParameterChecker methodRef = String::isEmpty;
    }
}
