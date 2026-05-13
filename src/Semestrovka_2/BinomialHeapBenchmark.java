package Semestrovka_2;

import java.io.*;
import java.util.*;


public class BinomialHeapBenchmark {
    private static final Random random = new Random();

    public static void main(String[] args) throws IOException {
        String testFilePath = "test_data_50x100000.txt";
        generateTestData(testFilePath);

        List<int[]> testArrays = loadTestData(testFilePath);

        try (PrintWriter csvWriter = new PrintWriter(new FileWriter("binomial_heap_results.csv"))) {
            csvWriter.println("size,operation,time_ns,iterations");

            for (int[] arr : testArrays) {
                int size = arr.length;

                // Операция вставки (с подсчётом итераций)
                BinomialHeap heapForInsert = new BinomialHeap();
                long start = System.nanoTime();
                for (int val : arr) heapForInsert.insertWithCounting(val);
                long time = System.nanoTime() - start;
                long iterations = heapForInsert.getOperationSteps();
                csvWriter.printf("%d,%s,%d,%d\n", size, "insert", time, iterations);

                // Операция поиска минимума
                BinomialHeap heapForFind = BinomialHeap.heapSortWithCounting(arr);
                long startFind = System.nanoTime();
                heapForFind.findMinWithCounting();
                long timeFind = System.nanoTime() - startFind;
                long iterationsFind = heapForFind.getOperationSteps();
                csvWriter.printf("%d,%s,%d,%d\n", size, "findMin", timeFind, iterationsFind);

                // Операция extractMin (удаление минимума для всех элементов)
                BinomialHeap heapForExtract = BinomialHeap.heapSortWithCounting(arr);
                long startExtract = System.nanoTime();
                for (int i = 0; i < size; i++) heapForExtract.extractMinWithCounting();
                long timeExtract = System.nanoTime() - startExtract;
                long iterationsExtract = heapForExtract.getOperationSteps();
                csvWriter.printf("%d,%s,%d,%d\n", size, "extractMin", timeExtract, iterationsExtract);

                // Операция слияния (merge) двух куч
                int mid = size / 2;
                BinomialHeap heap1 = new BinomialHeap();
                BinomialHeap heap2 = new BinomialHeap();
                for (int i = 0; i < mid; i++) heap1.insertWithCounting(arr[i]);
                for (int i = mid; i < size; i++) heap2.insertWithCounting(arr[i]);
                long startMerge = System.nanoTime();
                heap1.unionWithCounting(heap2);
                long timeMerge = System.nanoTime() - startMerge;
                long iterationsMerge = heap1.getOperationSteps() + heap2.getOperationSteps();
                csvWriter.printf("%d,%s,%d,%d\n", size, "merge", timeMerge, iterationsMerge);

                csvWriter.flush();
                System.out.println("Processed size: " + size);
            }
        }
        System.out.println("Benchmark completed! Results saved to 'binomial_heap_results.csv'");
    }

    private static void generateTestData(String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (int i = 0; i < 50; i++) {
                int size = 100 + random.nextInt(100_000 - 100 + 1);
                List<Integer> data = new ArrayList<>();
                for (int j = 0; j < size; j++) data.add(random.nextInt(100_000_000));
                // Можно оставить как есть (не сортировать) или отсортировать – по желанию
                writer.println(String.join(" ", data.stream().map(String::valueOf).toArray(String[]::new)));
            }
        }
    }

    private static List<int[]> loadTestData(String filePath) throws IOException {
        List<int[]> testArrays = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                int[] arr = new int[parts.length];
                for (int i = 0; i < parts.length; i++) arr[i] = Integer.parseInt(parts[i]);
                testArrays.add(arr);
            }
        }
        return testArrays;
    }
}