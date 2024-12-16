package org.example;

public class Dog extends Animal {
    Dog(Integer age, String name, Double weight) {
        super(age, name, weight);
    }

    @Override
    public Double getFeedInfoKg() {
        return getWeigth() * 0.3;
    }

    @Override
    public String toString() {
        return "Dog name = " + getName() + ", age = " + getAge() + ", mass = " + String.format("%.2f", getWeigth()) + ", feed = " + String.format("%.2f", getFeedInfoKg());
    }
}
