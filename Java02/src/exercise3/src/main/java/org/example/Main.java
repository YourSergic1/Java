package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Integer n = readAmount(in);
        List<Animal> pets = new ArrayList<>();
        readArr(in, n, pets);
        plusYear(pets);
        printArr(pets);
    }

    private static Integer readInt(Scanner scan) {
        while (!scan.hasNextInt()) {
            System.out.println("Couldn't parse a number. Please, try again");
            scan.next();
        }
        return scan.nextInt();
    }

    private static Integer readAmount(Scanner scan) {
        System.out.println("Enter amount of animals:");
        Integer n = readInt(scan);
        scan.nextLine();
        return n;
    }

    private static void readArr(Scanner scan, Integer n, List<Animal> arr) {
        n--;
        Animal pet = readOneOfArr(scan);
        if (pet != null) arr.add(pet);
        if (n > 0) readArr(scan, n, arr);
    }

    private static Animal readOneOfArr(Scanner scan) {
        String type = readType(scan);
        if (!type.equals("dog") && !type.equals("cat")) return null;
        String name = readName(scan);
        Integer age = readAge(scan);
        if (age <= 0) return null;
        scan.nextLine();
        return chooseType(type, name, age);
    }

    private static Animal chooseType(String type, String name, int age) {
        return switch (type) {
            case "cat" -> new Cat(age, name);
            case "dog" -> new Dog(age, name);
            default -> null;
        };
    }

    private static Integer readAge(Scanner scan) {
        System.out.println("Enter the age of animal:");
        Integer age = readInt(scan);
        if (age <= 0) System.out.println("Incorrect input. Age <= 0");
        return age;
    }

    private static void plusYear(List<Animal> arr) {
        if (arr.isEmpty()) return;
        List<Animal> updatedPets = arr.stream().peek(
                animal -> {
                    if (animal.getAge() > 10) animal.setAge(animal.getAge() + 1);
                }
        ).toList();
        arr.clear();
        arr.addAll(updatedPets);
    }


    private static String readName(Scanner scan) {
        System.out.println("Enter the name of animal:");
        return scan.nextLine();
    }

    private static String readType(Scanner scan) {
        System.out.println("Enter the type of animal:");
        String type = scan.nextLine();
        if (!type.equals("dog") && !type.equals("cat")) System.out.println("Incorrect input. Unsupported pet type");
        return type;
    }

    private static void printArr(List<Animal> arr) {
        if (arr.isEmpty()) return;
        arr.forEach(animal -> System.out.println(animal.toString()));
    }
}