package Semestrovka_1;

import java.io.*;
import java.util.Random;

public class DataGenerator {
    public static void main(String[] args) throws IOException {
        Random rand = new Random();
        int numSets = 30;

        try (PrintWriter pw = new PrintWriter("test_data_stooge.txt")) {
            for (int i = 0; i < numSets; i++) {
                int size = rand.nextInt(100, 2001); // максимум 2000
                for (int j = 0; j < size; j++)
                    pw.print(rand.nextInt(1, 10001) + (j < size-1 ? " " : ""));
                pw.println();
            }
        }

        try (PrintWriter pw = new PrintWriter("test_data_comb.txt")) {
            for (int i = 0; i < numSets; i++) {
                int size = rand.nextInt(100, 10001);
                for (int j = 0; j < size; j++)
                    pw.print(rand.nextInt(1, 10001) + (j < size-1 ? " " : ""));
                pw.println();
            }
        }
        System.out.println("Тестовые данные созданы.");
    }
}