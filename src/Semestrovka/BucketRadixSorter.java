package Semestrovka;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketRadixSorter {

    private static final int BUCKET_COUNT = 100;

    // Сортировка массива
    public long sort(int[] arr) {
        if (arr == null || arr.length <= 1) return 0;
        long[] iters = new long[1];
        bucketSortForArray(arr, iters);
        return iters[0];
    }

    // Сортировка списка
    public long sort(List<Integer> list) {
        if (list == null || list.size() <= 1) return 0;
        long[] iters = new long[1];
        bucketSortForList(list, iters);
        return iters[0];
    }

    // Bucket Sort для массива
    private void bucketSortForArray(int[] arr, long[] iters) {
        int n = arr.length;
        int min = arr[0], max = arr[0];
        for (int v : arr) {
            if (v < min) min = v;
            if (v > max) max = v;
        }
        if (min == max) return;

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] buckets = new ArrayList[BUCKET_COUNT];
        for (int i = 0; i < BUCKET_COUNT; i++) buckets[i] = new ArrayList<>();

        double range = (double) (max - min + 1);
        for (int val : arr) {
            int idx = (int) ((val - min) * BUCKET_COUNT / range);
            if (idx >= BUCKET_COUNT) idx = BUCKET_COUNT - 1;
            buckets[idx].add(val);
        }

        int pos = 0;
        for (ArrayList<Integer> bucket : buckets) {
            if (bucket.isEmpty()) continue;
            radixSortForList(bucket, iters);
            for (int v : bucket) {
                arr[pos++] = v;
            }
        }
    }

    // Bucket Sort для списка
    private void bucketSortForList(List<Integer> list, long[] iters) {
        int n = list.size();
        int min = list.get(0), max = list.get(0);
        for (int i = 1; i < n; i++) {
            int v = list.get(i);
            if (v < min) min = v;
            if (v > max) max = v;
        }
        if (min == max) return;

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] buckets = new ArrayList[BUCKET_COUNT];
        for (int i = 0; i < BUCKET_COUNT; i++) buckets[i] = new ArrayList<>();

        double range = (double) (max - min + 1);
        for (int val : list) {
            int idx = (int) ((val - min) * BUCKET_COUNT / range);
            if (idx >= BUCKET_COUNT) idx = BUCKET_COUNT - 1;
            buckets[idx].add(val);
        }

        list.clear();
        for (ArrayList<Integer> bucket : buckets) {
            if (bucket.isEmpty()) continue;
            radixSortForList(bucket, iters);
            list.addAll(bucket);
        }
    }

    // Radix Sort  для списка
    private void radixSortForList(List<Integer> list, long[] iters) {
        if (list.size() <= 1) return;

        int max = Collections.max(list);

        // Обрабатываем каждый разряд (основание 10)
        for (int exp = 1; max / exp > 0; exp *= 10) {
            @SuppressWarnings("unchecked")
            ArrayList<Integer>[] buckets = new ArrayList[10];
            for (int i = 0; i < 10; i++) buckets[i] = new ArrayList<>();

            // Распределяем элементы по корзинам
            for (int val : list) {
                int digit = (val / exp) % 10;
                buckets[digit].add(val);
                iters[0]++;   // каждая итерация распределения
            }

            // Собираем элементы обратно
            list.clear();
            for (int i = 0; i < 10; i++) {
                for (int val : buckets[i]) {
                    list.add(val);
                    iters[0]++;
                }
            }
        }
    }
}