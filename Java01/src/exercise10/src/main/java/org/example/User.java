package org.example;

import java.util.Scanner;

public class User {
    String name;
    int age;

    public static User readUser(Scanner scan) {
        User newOne = new User();
        newOne.name = scan.nextLine();
        while (!scan.hasNextInt()) {
            System.out.println("Couldn't parse a number. Please, try again");
            scan.next();
        }
        newOne.age = scan.nextInt();
        if (newOne.age <= 0) {
            System.out.println("Incorrect input. Age <= 0. Please try to add person again.");
            scan.nextLine();
            return null;
        }
        scan.nextLine();
        return newOne;
    }
}
