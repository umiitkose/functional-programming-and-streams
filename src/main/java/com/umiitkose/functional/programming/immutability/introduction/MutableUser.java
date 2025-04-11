package com.umiitkose.functional.programming.immutability.introduction;

import java.util.Objects;

public class MutableUser {
    private String name;
    private int age;

    public MutableUser(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MutableUser that = (MutableUser) o;
        return age == that.age && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "MutableUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public MutableUser getMutableUser() {
        return this;
    }

}
