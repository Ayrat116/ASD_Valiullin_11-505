package Semestrovka.BuckedSorts;

import java.io.*;
import java.util.*;

public class Benchmark {
    public static void main(String[] args) throws Exception {
        // Если файла с тестами нет – создаём
        if (!new File("test_data.txt").exists()) {
            TestsCreator.main(args);
        }

        // Читаем все строки
        List<int[]> allTests = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("test_data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] parts = line.trim().split("\\s+");
                int[] arr = new int[parts.length];
                for (int i = 0; i < parts.length; i++) arr[i] = Integer.parseInt(parts[i]);
                allTests.add(arr);
            }
        }

        // Результаты в CSV
        try (PrintWriter out = new PrintWriter("bucket_results.csv")) {
            out.println("size,time_ms,iterations");

            for (int[] data : allTests) {
                int maxVal = max(data);
                // Копии для замеров
                int[] forTime = Arrays.copyOf(data, data.length);
                long start = System.currentTimeMillis();
                BuckedSort.bucketSortArray(forTime, maxVal);
                long time = System.currentTimeMillis() - start;

                int[] forIter = Arrays.copyOf(data, data.length);
                long iters = BuckedSort.bucketSortArrayWithCounter(forIter, maxVal);

                out.println(data.length + "," + time + "," + iters);
                System.out.printf("Размер: %5d | время: %3d мс | итераций: %8d%n",
                        data.length, time, iters);
            }
        }
        System.out.println("\nРезультаты сохранены в bucket_results.csv");
    }

    private static int max(int[] arr) {
        int m = arr[0];
        for (int v : arr) if (v > m) m = v;
        return m;
    }
}