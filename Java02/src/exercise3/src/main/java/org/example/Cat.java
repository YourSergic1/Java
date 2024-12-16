package org.example;

public class Cat extends Animal {
    Cat(Integer age, String name) {
        super(age, name);
    }

    @Override
    public String toString() {
        return "Cat name = " + getName() + ", age = " + getAge() + ".";
    }
}