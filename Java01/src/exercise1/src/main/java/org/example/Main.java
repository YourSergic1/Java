package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите координаты х1:");
        double x1 = readDouble(in);
        System.out.println("Введите координаты y1:");
        double y1 = readDouble(in);
        System.out.println("Введите координаты х2:");
        double x2 = readDouble(in);
        System.out.println("Введите координаты y2:");
        double y2 = readDouble(in);
        System.out.println("Введите координаты х3:");
        double x3 = readDouble(in);
        System.out.println("Введите координаты y3:");
        double y3 = readDouble(in);
        double a = distance(x1, y1, x2, y2);
        double b = distance(x1, y1, x3, y3);
        double c = distance(x2, y2, x3, y3);
        if (a + b <= c || a + c <= b || b + c <= a) System.out.println("It isn't triangle");
        else System.out.printf("Perimeter: %.3f", a + b + c);
    }

    private static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    private static double readDouble(Scanner scan) {
        while (!scan.hasNextDouble()) {
            System.out.println("Couldn't parse a number. Please, try again");
            scan.next();
        }
        return scan.nextDouble();
    }
}