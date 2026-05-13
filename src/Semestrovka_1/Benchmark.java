package Semestrovka_1;

import java.io.*;
import java.util.*;

public class Benchmark {
    public static void main(String[] args) throws IOException {
        // Чтение всех тестов из файла
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

        // Файлы результатов
        try (PrintWriter outStooge = new PrintWriter("stooge_results.csv");
             PrintWriter outComb = new PrintWriter("comb_results.csv")) {

            outStooge.println("size;dataType;time_ns;iterations");
            outComb.println("size;dataType;time_ns;iterations");

            for (int[] data : allTests) {
                int size = data.length;

                // ----- STOOGE SORT -----
                // массив
                int[] copyArr = Arrays.copyOf(data, size);
                Counter cntArr = new Counter();
                long start = System.nanoTime();
                StoogeSort.sort(copyArr, cntArr);
                long timeArr = System.nanoTime() - start;
                outStooge.printf("%d;array;%d;%d\n", size, timeArr, cntArr.get());

                // список
                List<Integer> list = new ArrayList<>();
                for (int v : data) list.add(v);
                Counter cntList = new Counter();
                start = System.nanoTime();
                StoogeSort.sort(list, cntList);
                long timeList = System.nanoTime() - start;
                outStooge.printf("%d;list;%d;%d\n", size, timeList, cntList.get());

                // ----- COMB SORT -----
                // массив
                copyArr = Arrays.copyOf(data, size);
                cntArr = new Counter();
                start = System.nanoTime();
                CombSort.sort(copyArr, cntArr);
                timeArr = System.nanoTime() - start;
                outComb.printf("%d;array;%d;%d\n", size, timeArr, cntArr.get());

                // список
                list = new ArrayList<>();
                for (int v : data) list.add(v);
                cntList = new Counter();
                start = System.nanoTime();
                CombSort.sort(list, cntList);
                timeList = System.nanoTime() - start;
                outComb.printf("%d;list;%d;%d\n", size, timeList, cntList.get());

                outStooge.flush();
                outComb.flush();
                System.out.println("Обработан размер: " + size);
            }
        }
        System.out.println("Результаты сохранены в 'stooge_results.csv' и 'comb_results.csv'");
    }
}