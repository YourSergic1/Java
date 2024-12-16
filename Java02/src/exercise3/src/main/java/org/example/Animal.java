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

    public void setAge(Integer newAge) {
        this.age = newAge;
    }

    public String getName() {
        return name;
    }

    abstract public String toString();
}
