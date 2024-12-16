package org.example;

abstract public class Animal {
    private Integer age;
    private String name;
    private Double weigth;

    Animal(Integer age, String name, Double weigth) {
        this.age = age;
        this.name = name;
        this.weigth = weigth;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Double getWeigth() {
        return weigth;
    }

    abstract public Double getFeedInfoKg();

    abstract public String toString();
}
