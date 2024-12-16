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
        AnimalIterator forPrint = new AnimalIterator(pets);
        while (forPrint.hasNext()) {
            System.out.println(forPrint.next().toString());
        }
    }

    private static Integer readInt(Scanner scan) {
        while (!scan.hasNextInt()) {
            System.out.println("Couldn't parse a number. Please, try again");
            scan.next();
        }
        return scan.nextInt();
    }

    private static Animal readArr(Scanner scan) {
        String[] typeOfAnimals = {"dog", "cat", "hamster", "guinea"};
        System.out.println("Enter the type of animal:");
        String type = scan.nextLine();
        if (!type.equals(typeOfAnimals[0]) && !type.equals(typeOfAnimals[1]) && !type.equals(typeOfAnimals[2]) && !type.equals(typeOfAnimals[3])) {
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
        scan.nextLine();
        return switch (type) {
            case "cat" -> new Cat(age, name);
            case "dog" -> new Dog(age, name);
            default -> null;
        };
    }
}