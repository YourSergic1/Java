package org.example;

public class Cat extends Animal {
    Cat(Integer age, String name, Double weight) {
        super(age, name, weight);
    }

    @Override
    public Double getFeedInfoKg() {
        return getWeigth() * 0.1;
    }

    @Override
    public String toString() {
        return "Cat name = " + getName() + ", age = " + getAge() + ", mass = " + String.format("%.2f", getWeigth()) + ", feed = " + String.format("%.2f", getFeedInfoKg());
    }
}