package org.example;

public class GuineaPig extends Animal implements Herbivore {
    GuineaPig(Integer age, String name) {
        super(age, name);
    }

    @Override
    public String toString() {
        return "GuineaPig name = " + getName() + ", age = " + getAge() + ". " + chill();
    }

    public String chill() {
        return "I can chill for 12 hours";
    }
}