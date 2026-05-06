package Semestrovka.RadixSort;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static Semestrovka.RadixSort.Radix.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. Генерация тестовых данных (50 наборов, размер 100..10000)
        Random rand = new Random();
        int numTests = 50;
        String testFile = "test_data_radix.txt";

        try (FileWriter fw = new FileWriter(testFile)) {
            for (int i = 0; i < numTests; i++) {
                int size = rand.nextInt(100, 10001);
                for (int j = 0; j < size; j++) {
                    fw.write(String.valueOf(rand.nextInt(1, 10001)));
                    if (j < size - 1) fw.write(" ");
                }
                fw.write("\n");
            }
        }
        System.out.println("Сгенерировано " + numTests + " тестов в " + testFile);

        // 2. Чтение всех наборов из файла
        List<int[]> allTests = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(testFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] parts = line.trim().split("\\s+");
                int[] arr = new int[parts.length];
                for (int i = 0; i < parts.length; i++) arr[i] = Integer.parseInt(parts[i]);
                allTests.add(arr);
            }
        }

        // 3. Замеры и запись в CSV
        try (PrintWriter out = new PrintWriter("radix_results.csv")) {
            out.println("size;time_array_ms;iterations_array;time_list_ms;iterations_list");
            for (int[] data : allTests) {
                int size = data.length;

                // ----- массив int[] -----
                int[] copyArr = Arrays.copyOf(data, size);
                long start = System.currentTimeMillis();
                radixSortArray(copyArr);
                long timeArr = System.currentTimeMillis() - start;

                int[] copyArr2 = Arrays.copyOf(data, size);
                long iterArr = radixSortArrayWithCounter(copyArr2);

                // ----- List<Integer> -----
                List<Integer> list = new ArrayList<>();
                for (int v : data) list.add(v);
                long startList = System.currentTimeMillis();
                radixSortList(list);
                long timeList = System.currentTimeMillis() - startList;

                List<Integer> list2 = new ArrayList<>();
                for (int v : data) list2.add(v);
                long iterList = radixSortListWithCounter(list2);

                out.printf("%d;%d;%d;%d;%d\n", size, timeArr, iterArr, timeList, iterList);
                System.out.printf("Размер: %5d | массив: %3d мс / %8d итер | список: %3d мс / %8d итер%n",
                        size, timeArr, iterArr, timeList, iterList);
            }
        }
        System.out.println("\nРезультаты сохранены в radix_results.csv");
    }
}
