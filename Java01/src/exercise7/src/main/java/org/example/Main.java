package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter the path to the file:");
        Scanner in = new Scanner(System.in);
        String filename = "./src/main/resources/" + in.nextLine();
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Input error. File isn't exist");
            return;
        }
        try {
            int n = readerAmount(file);
            if (n <= 0) {
                System.out.println("Input error. Size <= 0");
                return;
            }
            System.out.println(n);
            double[] arr = new double[n];
            if (!readerArray(file, n, arr)) {
                System.out.println("Input error. Insufficient number of elements»");
                return;
            }
            for (int i = 0; i < n; i++) {
                if (i != n - 1) System.out.printf("%.1f ", arr[i]);
                else System.out.printf("%.1f\n", arr[i]);
            }
            System.out.println("Saving min and max values in file");
            double min = findMin(arr);
            double max = findMax(arr);
            minMaxWriter(min, max);
        } catch (FileNotFoundException e) {
            System.out.println("Input error. File isn't exist");
        }
    }


    private static int readerAmount(File file) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNext()) {
            if (fileScanner.hasNextInt()) return fileScanner.nextInt();
            else fileScanner.next();
        }
        return -1;
    }

    private static boolean readerArray(File file, int n, double[] arr) throws FileNotFoundException {
        Scanner fileReader = new Scanner(file);
        int count = -1;
        while (fileReader.hasNext() && count != n) {
            if (fileReader.hasNextDouble()) {
                if (count == -1) fileReader.next();
                else arr[count] = fileReader.nextDouble();
                count++;
            } else fileReader.next();
        }
        return count == n;
    }

    private static double findMin(double[] arr) {
        double min = arr[0];
        for (double each : arr) {
            if (min > each) min = each;
        }
        return min;
    }

    private static double findMax(double[] arr) {
        double max = arr[0];
        for (double each : arr) {
            if (max < each) max = each;
        }
        return max;
    }

    private static void minMaxWriter(double min, double max) {
        try (FileWriter writer = new FileWriter("./src/main/resources/result.txt", false)) {
            writer.write(min + " " + max);
        } catch (Exception e) {
            System.out.println("Error of writing into file.");
        }
    }
}