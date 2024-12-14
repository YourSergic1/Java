package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input amount of strings:");
        int n = readInt(in);
        System.out.println("Input the strings:");
        List<String> arr = readStringOfArr(in, n);
        System.out.println("Input pattern string:");
        String pattern = readPattern(in);
        serch(arr, pattern);
    }

    private static int readInt(Scanner scan) {
        while (!scan.hasNextInt()) {
            System.out.println("Couldn't parse a number. Please, try again");
            scan.next();
        }
        return scan.nextInt();
    }

    private static List<String> readStringOfArr(Scanner scan, int n) {
        scan.nextLine();
        List<String> arr = new ArrayList<>();
        while (n > 0) {
            arr.add(scan.nextLine());
            n--;
        }
        return arr;
    }

    private static String readPattern(Scanner scan) {
        return scan.nextLine();
    }

    private static void serch(List<String> arr, String pattern) {
        int count = 0;
        for (String box : arr) {
            for (int j = 0; j < box.length(); j++) {
                if (box.charAt(j) == pattern.charAt(0)) {
                    if (checkWord(box, pattern, j)) {
                        if (count == 0) {
                            System.out.print(box);
                        } else {
                            System.out.print(", " + box);
                        }
                        count++;
                        break;
                    }
                }
            }
        }
        if (count > 0) System.out.print("\n");
    }

    private static boolean checkWord(String str1, String str2, int pos) {
        boolean flag = true;
        if (pos + str2.length() > str1.length()) {
            return false;
        }
        for (int j = 0; j < str2.length(); j++) {
            if (str1.charAt(j + pos) != str2.charAt(j)) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}