package org.example;

public class Cat extends Animal implements Omnivore {
    Cat(Integer age, String name) {
        super(age, name);
    }


    @Override
    public String toString() {
        return "Cat name = " + getName() + ", age = " + getAge() + ". " + hunt();
    }

    @Override
    public String hunt() {
        return "I can hunt for mice";
    }
}