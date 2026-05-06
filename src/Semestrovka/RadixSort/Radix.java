package Semestrovka.RadixSort;

import java.io.*;
import java.util.*;

public class Radix {

    // ======================== RADIX SORT ========================

    // Counting Sort по разряду (для массива)
    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];
        for (int num : arr) count[(num / exp) % 10]++;
        for (int i = 1; i < 10; i++) count[i] += count[i - 1];
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }
        System.arraycopy(output, 0, arr, 0, n);
    }

    // Radix Sort для массива
    public static void radixSortArray(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int max = arr[0];
        for (int num : arr) if (num > max) max = num;
        for (int exp = 1; max / exp > 0; exp *= 10)
            countingSortByDigit(arr, exp);
    }

    // Radix Sort для List<Integer>
    public static void radixSortList(List<Integer> list) {
        if (list == null || list.size() <= 1) return;
        int max = Collections.max(list);
        for (int exp = 1; max / exp > 0; exp *= 10)
            countingSortByDigit(list, exp);
    }

    // Counting Sort для List по разряду
    private static void countingSortByDigit(List<Integer> list, int exp) {
        int n = list.size();
        Integer[] output = new Integer[n];
        int[] count = new int[10];
        for (int num : list) count[(num / exp) % 10]++;
        for (int i = 1; i < 10; i++) count[i] += count[i - 1];
        for (int i = n - 1; i >= 0; i--) {
            int num = list.get(i);
            int digit = (num / exp) % 10;
            output[count[digit] - 1] = num;
            count[digit]--;
        }
        for (int i = 0; i < n; i++) list.set(i, output[i]);
    }

    // ---------- с подсчётом итераций (массив) ----------
    public static long radixSortArrayWithCounter(int[] arr) {
        if (arr == null || arr.length <= 1) return 0;
        long iterations = 0;
        int max = arr[0];
        for (int num : arr) if (num > max) max = num;
        for (int exp = 1; max / exp > 0; exp *= 10)
            iterations += countingSortByDigitWithCounter(arr, exp);
        return iterations;
    }

    private static long countingSortByDigitWithCounter(int[] arr, int exp) {
        long it = 0;
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];
        for (int num : arr) {
            count[(num / exp) % 10]++;
            it++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
            it++;
        }
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
            it++;
        }
        System.arraycopy(output, 0, arr, 0, n);
        it += n;
        return it;
    }

    // ---------- с подсчётом итераций (List) ----------
    public static long radixSortListWithCounter(List<Integer> list) {
        if (list == null || list.size() <= 1) return 0;
        long iterations = 0;
        int max = Collections.max(list);
        for (int exp = 1; max / exp > 0; exp *= 10)
            iterations += countingSortByDigitWithCounter(list, exp);
        return iterations;
    }

    private static long countingSortByDigitWithCounter(List<Integer> list, int exp) {
        long it = 0;
        int n = list.size();
        Integer[] output = new Integer[n];
        int[] count = new int[10];
        for (int num : list) {
            count[(num / exp) % 10]++;
            it++;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
            it++;
        }
        for (int i = n - 1; i >= 0; i--) {
            int num = list.get(i);
            int digit = (num / exp) % 10;
            output[count[digit] - 1] = num;
            count[digit]--;
            it++;
        }
        for (int i = 0; i < n; i++) list.set(i, output[i]);
        it += n;
        return it;
    }

    // ======================== MAIN ========================
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