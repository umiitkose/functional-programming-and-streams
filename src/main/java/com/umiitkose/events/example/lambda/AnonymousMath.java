package com.umiitkose.events.example.lambda;

public class AnonymousMath {

    void main() {
        Math math = new Math() {
            @Override
            public double calculate(int a, int b) {
                return a + b;
            }
        };

        System.out.println(math.calculate(5, 10));

        Math math2 = new Math() {
            @Override
            public double calculate(int a, int b) {
                return a - b;
            }
        };
        System.out.println(math2.calculate(5, 10));

        Math math3 = new Math() {
            @Override
            public double calculate(int a, int b) {
                return a * b;
            }
        };

        System.out.println(math3.calculate(5, 10));

        Math math4 = new Math() {
            @Override
            public double calculate(int a, int b) {
                return (double) a / b;
            }
        };

        System.out.println(math4.calculate(5, 10));
    }
}
