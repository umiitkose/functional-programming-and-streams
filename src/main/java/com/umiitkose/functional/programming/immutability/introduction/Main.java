package com.umiitkose.functional.programming.immutability.introduction;

public class Main {
    MutableUser mutableUser;

    void main() {
        ImmutableUser immutableUser = new ImmutableUser("Ümit", 32);
        System.out.println(immutableUser);

        mutableUser = new MutableUser("Ali", 27);
        System.out.println(mutableUser);

        System.out.println("new immutableUser: " + immutableUser.getMutableUser(mutableUser));

        System.out.println(mutableUser);

    }


}
