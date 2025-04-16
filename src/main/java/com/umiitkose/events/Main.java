package com.umiitkose.events;

public class Main {
    public static void main(String[] args) {
        String s = "Hello World";
        Runnable r = () -> System.out.println("Hello World" + s);
        Runnable helloWorld = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World" + s);
            }
        };
    }
}
