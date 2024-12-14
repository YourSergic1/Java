package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter amount of numbers:");
        int n = readInt(in);
        if (n <= 0) System.out.println("Input error. Size <= 0");
        else {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                System.out.println("Enter a number:");
                arr[i] = readInt(in);
            }
            int count = 0;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i] < 0) {
                    sum += arr[i];
                    count++;
                }
            }
            if (count == 0) System.out.println("There are no negative elements");
            else System.out.println(sum / count);
        }
    }

    private static int readInt(Scanner scan) {
        while (!scan.hasNextInt()) {
            System.out.println("Couldn't parse a number. Please, try again");
            scan.next();
        }
        return scan.nextInt();
    }
}