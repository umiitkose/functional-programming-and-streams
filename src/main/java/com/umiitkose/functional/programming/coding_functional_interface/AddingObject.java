package com.umiitkose.functional.programming.coding_functional_interface;

@FunctionalInterface
public interface AddingObject {

    void test();

    String toString();

    public abstract int hashCode();

    public boolean equals(Object o);

    //public boolean equals(String o); Eğer Object dışında geçersen compile error
}
