package com.umiitkose.events.example.lambda;

public class LambdaMath {

    void main() {

        Math math = (a, b) -> a + b;
        System.out.println(math.calculate(5, 10));

        Math math2 = (a, b) -> a - b;
        System.out.println(math2.calculate(5, 10));

        Math math3 = (a, b) -> a * b;
        System.out.println(math3.calculate(5, 10));

        Math math4 = (a, b) -> a / b;
        System.out.println(math4.calculate(5, 10));

        Util.calculate(new Math() {
            @Override
            public double calculate(int a, int b) {
                return a + b;
            }
        }, 5, 10);
    }


}
