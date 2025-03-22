package com.umiitkose.functional.programming.using_method_reference.instance_method_on_particular_object;

public class Main {
    public static void main(String[] args) {
        var str = "";
        StringChecker lambda = () ->
                str.startsWith("Zoo");

        //StringChecker methodReference = str::startsWith;
    }
}
