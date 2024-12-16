package org.example;

import java.util.concurrent.TimeUnit;

public class Cat extends Animal {
    Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Cat name = " + getName() + ", age = " + getAge();
    }

    @Override
    public Double goToWalk() {
        Double time = getAge() * 0.25;
        try {
            TimeUnit.SECONDS.sleep(time.longValue());
        } catch (Exception e) {
            System.out.println("Walk problem.");
        }
        return time;
    }
}
