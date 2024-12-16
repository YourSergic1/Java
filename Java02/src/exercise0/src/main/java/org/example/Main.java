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
        if (n <= 0) {
            System.out.println("Incorrect input. Amount <= 0");
            return;
        }
        List<Animal> pets = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Animal newOne = readArr(in);
            if (newOne != null) pets.add(newOne);
        }
        for (Animal each : pets) System.out.println(each.toString());
    }

    private static Integer readInt(Scanner scan) {
        while (!scan.hasNextInt()) {
            System.out.println("Couldn't parse a number. Please, try again");
            scan.next();
        }
        return scan.nextInt();
    }

    private static Animal readArr(Scanner scan) {
        System.out.println("Enter the type:");
        String type = scan.nextLine();
        if (type.equals("dog") || type.equals("cat")) {
            System.out.println("Enter the name:");
            String name = scan.nextLine();
            System.out.println("Enter the age:");
            Integer age = readInt(scan);
            scan.nextLine();
            if (age <= 0) {
                System.out.println("Incorrect input. Age <= 0");
                return null;
            }
            if (type.equals("dog")) return new Dog(name, age);
            if (type.equals("cat")) return new Cat(name, age);
        } else System.out.println("Incorrect input. Unsupported pet type");
        return null;
    }

}
