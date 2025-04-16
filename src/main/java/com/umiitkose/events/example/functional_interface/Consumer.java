package com.umiitkose.events.example.functional_interface;

@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);

    default Consumer<T> andThen(Consumer<? super T> after) {
        return t -> {
            accept(t);
            after.accept(t);
        };
    }


}
