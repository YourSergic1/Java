package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter amount of numbers:");
        Scanner in = new Scanner(System.in);
        int n = readInt(in);
        if (n <= 0) System.out.println("Input error. Size <= 0");
        else {
            double[] arr = new double[n];
            for (int i = 0; i < n; i++) {
                System.out.println("Enter a number:");
                arr[i] = readDouble(in);
            }
            sort(arr, n);
            for (int i = 0; i < n; i++) {
                if (i != n - 1) System.out.printf("%.1f ", arr[i]);
                else System.out.printf("%.1f\n", arr[i]);
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

    private static double readDouble(Scanner scan) {
        while (!scan.hasNextDouble()) {
            System.out.println("Couldn't parse a number. Please, try again");
            scan.next();
        }
        return scan.nextDouble();
    }

    private static void sort(double[] arr, int n) {
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = 1 + i; j < n; j++) {
                if (arr[j] < arr[minIndex]) minIndex = j;
            }
            if (minIndex != i) {
                double box = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = box;
            }
        }
    }
}