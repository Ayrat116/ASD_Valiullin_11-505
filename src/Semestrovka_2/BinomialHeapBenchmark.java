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

                // Операция вставки
                measureOperation(csvWriter, size, "insert", () -> {
                    BinomialHeap heap = new BinomialHeap();
                    for (int val : arr) heap.insert(val);
                });

                // Операция поиска минимума
                BinomialHeap heapForFind = BinomialHeap.heapSort(arr);
                measureOperation(csvWriter, size, "findMin", () -> heapForFind.findMin());

                // Операция extractMin
                measureOperation(csvWriter, size, "extractMin", () -> {
                    BinomialHeap heap = BinomialHeap.heapSort(arr);
                    for (int i = 0; i < size; i++) heap.extractMin();
                });

                // Операция слияния двух куч
                measureOperation(csvWriter, size, "merge", () -> {
                    int mid = size / 2;
                    BinomialHeap heap1 = new BinomialHeap();
                    BinomialHeap heap2 = new BinomialHeap();
                    for (int i = 0; i < mid; i++) heap1.insert(arr[i]);
                    for (int i = mid; i < size; i++) heap2.insert(arr[i]);
                    heap1.union(heap2);
                });
            }
        }
        System.out.println("Benchmark completed! Results saved to 'binomial_heap_results.csv'");
    }

    private static void measureOperation(PrintWriter csvWriter, int size, String operation, Runnable task) {
        BinomialHeap heap = new BinomialHeap();
        heap.resetOperationSteps();

        // Stopwatch (имитация через System.nanoTime)
        long startTime = System.nanoTime();
        task.run();
        long endTime = System.nanoTime();
        long timeNano = endTime - startTime;

        System.gc();
        try { Thread.sleep(10); } catch (InterruptedException e) {}

        long startTime2 = System.nanoTime();
        task.run();
        long endTime2 = System.nanoTime();
        long timeNano2 = endTime2 - startTime2;

        long finalTime = (timeNano + timeNano2) / 2;

        long iterations = heap.getOperationSteps();
        csvWriter.printf("%d,%s,%d,%d\n", size, operation, finalTime, iterations);
        csvWriter.flush();
    }

    private static void generateTestData(String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (int i = 0; i < 50; i++) {
                int size = 100 + random.nextInt(100_000 - 100 + 1);
                List<Integer> sortedData = new ArrayList<>();
                for (int j = 0; j < size; j++) sortedData.add(random.nextInt(100_000_000));
                Collections.sort(sortedData);
                writer.println(String.join(" ", sortedData.stream().map(String::valueOf).toArray(String[]::new)));
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