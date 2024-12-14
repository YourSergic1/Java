package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter amount of seconds:");
        int sec = readInt(in);
        int min = 0;
        int hour = 0;
        if (sec < 0) System.out.println("Incorrect time");
        else {
            int[] time = countTime(sec, min, hour);
            printTime(time);
        }
    }

    private static int readInt(Scanner scan) {
        while (!scan.hasNextInt()) {
            System.out.println("Couldn't parse a number. Please, try again»");
            scan.next();
        }
        return scan.nextInt();
    }

    private static void printTime(int[] time) {
        System.out.printf("%d:%d:%d", time[0], time[1], time[2]);
    }

    private static int[] countTime(int sec, int min, int hour) {
        if (sec >= 60) {
            min = sec / 60;
            sec = sec % 60;
        }
        if (min >= 60) {
            hour = min / 60;
            min = min % 60;
        }
        return new int[]{hour, min, sec};
    }
}