package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter amount of numbers:");
        int n = readInt(in);
        if (n <= 0) System.out.println("Input error. Size <= 0");
        else {
            int[] arr = new int[n];
            int i = 0;
            while (i != n) {
                System.out.println("Enter a number:");
                arr[i] = readInt(in);
                i++;
            }
            ArrayList<Integer> arrParsed = new ArrayList<>();
            i = 0;
            while (i != n) {
                if (parseArr(arr[i])) arrParsed.add(arr[i]);
                i++;
            }
            if (arrParsed.isEmpty()) System.out.println("There are no such elements");
            else {
                i = 0;
                while (i != arrParsed.size()) {
                    System.out.println(arrParsed.get(i));
                    i++;
                }
            }
        }
    }

    private static int readInt(Scanner scan) {
        while (!scan.hasNextInt()) {
            System.out.println("Couldn't parse a number. Please, try again");
            scan.next();
        }
        return scan.nextInt();
    }

    private static boolean parseArr(int n) {
        int last = n % 10;
        while (n >= 10) {
            n = n / 10;
        }
        return last == n;
    }
}