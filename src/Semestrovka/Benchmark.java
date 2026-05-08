package Semestrovka;

import java.io.*;
import java.util.*;

public class Benchmark {

    public static void main(String[] args) throws IOException {
        // 1. Генерация данных, если файла ещё нет
        File dataFile = new File("test_data.txt");
        if (!dataFile.exists()) {
            DataGenerator.main(args);
        }

        // 2. Чтение всех наборов из файла
        List<int[]> arrays = new ArrayList<>();
        List<List<Integer>> lists = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split("\\s+");
                int[] arr = new int[parts.length];
                List<Integer> list = new ArrayList<>(parts.length);
                for (int i = 0; i < parts.length; i++) {
                    int val = Integer.parseInt(parts[i]);
                    arr[i] = val;
                    list.add(val);
                }
                arrays.add(arr);
                lists.add(list);
            }
        }

        System.out.println("Запуск тестирования...\n");
        System.out.printf("%-10s %-20s %-20s %-20s %-20s%n",
                "Размер", "Время массив(ns)", "Итераций массив", "Время список(ns)", "Итераций список");
        System.out.println("--------------------------------------------------------------------------------");

        BucketRadixSorter sorter = new BucketRadixSorter();

        // 3. Запись результатов в CSV
        try (PrintWriter csv = new PrintWriter(new FileWriter("results.csv"))) {
            csv.println("Size,ArrayTime_ns,ArrayIterations,ListTime_ns,ListIterations");

            for (int idx = 0; idx < arrays.size(); idx++) {
                int[] arrOrig = arrays.get(idx);
                List<Integer> listOrig = lists.get(idx);
                int size = arrOrig.length;

                // Копии для сортировки
                int[] arrCopy = arrOrig.clone();
                List<Integer> listCopy = new ArrayList<>(listOrig);

                // ----- Тест для массива -----
                long startArr = System.nanoTime();
                long itersArr = sorter.sort(arrCopy);
                long timeArr = System.nanoTime() - startArr;

                // ----- Тест для списка -----
                long startList = System.nanoTime();
                long itersList = sorter.sort(listCopy);
                long timeList = System.nanoTime() - startList;

                // Проверка корректности (опционально)
                if (!isSorted(arrCopy)) System.err.println("Ошибка: массив не отсортирован, набор " + idx);
                if (!isSorted(listCopy)) System.err.println("Ошибка: список не отсортирован, набор " + idx);

                System.out.printf("%-10d %-20d %-20d %-20d %-20d%n",
                        size, timeArr, itersArr, timeList, itersList);

                csv.printf("%d,%d,%d,%d,%d%n", size, timeArr, itersArr, timeList, itersList);
            }
        }

        System.out.println("\nРезультаты сохранены в файл results.csv");
    }

    private static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i-1] > a[i]) return false;
        }
        return true;
    }

    private static boolean isSorted(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i-1) > list.get(i)) return false;
        }
        return true;
    }
}