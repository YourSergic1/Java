package org.example;

public class Dog extends Animal implements Omnivore {
    Dog(Integer age, String name) {
        super(age, name);
    }

    @Override
    public String toString() {
        return "Dog name = " + getName() + ", age = " + getAge() + ". " + hunt();
    }

    @Override
    public String hunt() {
        return "I can hunt for robbers";
    }
}
