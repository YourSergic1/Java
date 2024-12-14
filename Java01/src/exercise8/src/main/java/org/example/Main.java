package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите число:");
        int[] count = {0};
        int[] num = {0};
        boolean flag = readInt(num, count, in);
        if (!flag) {
            System.out.println("Input error");
            return;
        }
        int box = num[0] - 1;
        while (flag && (box < num[0])) {
            box = num[0];
            flag = readInt(num, count, in);
        }
        if (!flag) System.out.println("The sequence is ordered in ascending order");
        else System.out.printf("The sequence is not ordered from the ordinal number of the number %d\n", count[0] - 2);
    }

    private static boolean readInt(int[] box, int[] count, Scanner scan) {
        boolean flag = true;
        count[0]++;
        if (scan.hasNextInt()) {
            box[0] = scan.nextInt();
        } else {
            flag = false;
        }
        return flag;
    }
}