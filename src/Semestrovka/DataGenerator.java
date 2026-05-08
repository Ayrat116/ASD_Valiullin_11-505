package Semestrovka;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataGenerator {
    private static final int NUM_SETS = 50;
    private static final int MIN_SIZE = 100;
    private static final int MAX_SIZE = 10000;
    private static final int VALUE_BOUND = 10001;

    public static void main(String[] args) throws IOException {
        Random rnd = new Random();
        try (FileWriter fw = new FileWriter("test_data.txt")) {
            for (int i = 0; i < NUM_SETS; i++) {
                int size = rnd.nextInt(MIN_SIZE, MAX_SIZE + 1);
                for (int j = 0; j < size; j++) {
                    fw.write(String.valueOf(rnd.nextInt(1, VALUE_BOUND)));
                    if (j < size - 1) fw.write(" ");
                }
                fw.write("\n");
            }
        }
        System.out.println("Сгенерирован файл test_data.txt с " + NUM_SETS + " наборами.");
    }
}