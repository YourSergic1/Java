package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter amount of Users:");
        int n = readInt(in);
        if (n <= 0) {
            System.out.println("Incorrect amount!");
            return;
        }
        List<User> arr = new ArrayList<>();
        while (arr.size() < n) {
            User user = User.readUser(in);
            if (user != null) arr.add(user);
        }
        List<User> arrSorted = arr.stream().filter(user -> user.age >= 18).toList();
        if (!arrSorted.isEmpty()) {
            for (int i = 0; i < arrSorted.size(); i++) {
                if (i == 0) System.out.print(arrSorted.get(i).name);
                else System.out.print(", " + arrSorted.get(i).name);
            }
            System.out.print("\n");
        }
    }

    private static int readInt(Scanner scan) {
        while (!scan.hasNextInt()) {
            System.out.println("Couldn't parse a number. Please, try again");
            scan.next();
        }
        int n = scan.nextInt();
        scan.nextLine();
        return n;
    }
}