package org.example;

import java.util.concurrent.TimeUnit;

public class Dog extends Animal {
    Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Dog name = " + getName() + ", age = " + getAge();
    }

    @Override
    public Double goToWalk() {
        Double time = getAge() * 0.5;
        try {
            TimeUnit.SECONDS.sleep(time.longValue());
        } catch (Exception e) {
            System.out.println("Walk problem.");
        }
        return time;
    }
}
