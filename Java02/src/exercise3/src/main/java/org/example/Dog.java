package org.example;

public class Dog extends Animal {
    Dog(Integer age, String name) {
        super(age, name);
    }

    @Override
    public String toString() {
        return "Dog name = " + getName() + ", age = " + getAge() + ".";
    }
}
