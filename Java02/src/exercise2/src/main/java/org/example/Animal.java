package org.example;

abstract public class Animal {
    private Integer age;
    private String name;

    Animal(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    abstract public String toString();
}
