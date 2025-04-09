package com.umiitkose.functional.programming.immutability.record;

import java.util.Objects;

public record RecordFeatures<T>(String name, int age, T value) {
    public RecordFeatures {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(value, "Value cannot be null");
    }

    @Override
    public String name() {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        return name;
    }

    @Override
    public int age() {
        return age;
    }

    public boolean isAdult() {
        return age >= 18;
    }

}


