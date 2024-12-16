package org.example;

public class Hamster extends Animal implements Herbivore {
    Hamster(Integer age, String name) {
        super(age, name);
    }

    @Override
    public String toString() {
        return "Hamster name = " + getName() + ", age = " + getAge() + ". " + chill();
    }

    public String chill() {
        return "I can chill for 8 hours";
    }
}
