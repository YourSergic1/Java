package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a number:");
        int n = readInt(in);
        if (n == 0) System.out.println(0);
        else if (n > 46 || n < -46) System.out.println("Too large n");
        else {
            boolean sign = n < 0;
            if (sign) n *= -1;
            n = fibo(n);
            if (sign) n *= -1;
            System.out.println(n);
        }
    }

    private static int readInt(Scanner scan) {
        while (!scan.hasNextInt()) {
            System.out.println("Couldn't parse a number. Please, try again");
            scan.next();
        }
        return scan.nextInt();
    }

    private static int fibo(int n) {
        int num;
        if (n == 0) num = 0;
        else if (n == 1) num = 1;
        else num = fibo(n - 1) + fibo(n - 2);
        return num;
    }
}