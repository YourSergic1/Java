package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter amount of animals:");
        Integer n = readInt(in);
        in.nextLine();
        List<Animal> pets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Animal newOne = readArr(in);
            if (newOne != null) {
                pets.add(newOne);
            }
        }
        for (Animal animal : pets) System.out.println(animal.toString());
    }

    private static Integer readInt(Scanner scan) {
        while (!scan.hasNextInt()) {
            System.out.println("Couldn't parse a number. Please, try again");
            scan.next();
        }
        return scan.nextInt();
    }

    private static Double readDouble(Scanner scan) {
        while (!scan.hasNextDouble()) {
            System.out.println("Couldn't parse a number. Please, try again");
            scan.next();
        }
        return scan.nextDouble();
    }

    private static Animal readArr(Scanner scan) {
        System.out.println("Enter the type of animal:");
        String type = scan.nextLine();
        if (!type.equals("cat") && !type.equals("dog")) {
            System.out.println("Incorrect input. Unsupported pet type");
            return null;
        }
        System.out.println("Enter the name of animal:");
        String name = scan.nextLine();
        System.out.println("Enter the age of animal:");
        Integer age = readInt(scan);
        if (age <= 0) {
            System.out.println("Incorrect input. Age <= 0");
            return null;
        }
        System.out.println("Enter the weight of animal:");
        Double weight = readDouble(scan);
        if (weight <= 0) {
            System.out.println("Incorrect input. Mass <= 0");
            return null;
        }
        scan.nextLine();
        if (type.equals("cat")) return new Cat(age, name, weight);
        if (type.equals("dog")) return new Dog(age, name, weight);
        return null;
    }
}