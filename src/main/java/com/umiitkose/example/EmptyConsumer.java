package com.umiitkose.example;

@FunctionalInterface
public interface EmptyConsumer {
    void accept();

    default EmptyConsumer andThen(EmptyConsumer after) {
        return () -> {
            accept();
            after.accept();
        };
    }
}
