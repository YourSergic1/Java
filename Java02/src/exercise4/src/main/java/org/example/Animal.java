package org.example;

abstract public class Animal {
    private String name;
    private int age;

    Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    abstract public String toString();

    abstract public Double goToWalk();
}
