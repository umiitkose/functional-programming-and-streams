package com.umiitkose.events.example.using_method_reference.instance_method_on_particular_object;

public class Main {
    public static void main(String[] args) {
        var str = "";
        StringChecker lambda = (s) ->
                str.startsWith(s);
        StringChecker methodReference = str::startsWith;
    }
}
