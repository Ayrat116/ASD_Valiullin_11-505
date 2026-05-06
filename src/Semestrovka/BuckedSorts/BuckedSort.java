package Semestrovka.BuckedSorts;

import java.util.*;

public class BuckedSort {

    // ======================== BUCKET SORT ========================

    // для массива int[]
    public static void bucketSortArray(int[] arr, int maxVal) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;
        List<Integer>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) buckets[i] = new ArrayList<>();

        for (int num : arr) {
            int idx = (int) ((long) num * n / (maxVal + 1L));
            buckets[idx].add(num);
        }
        for (List<Integer> bucket : buckets) Collections.sort(bucket);
        int idx = 0;
        for (List<Integer> bucket : buckets) {
            for (int num : bucket) arr[idx++] = num;
        }
    }

    // для List<Integer>
    public static void bucketSortList(List<Integer> list, int maxVal) {
        if (list == null || list.size() <= 1) return;
        int n = list.size();
        List<List<Integer>> buckets = new ArrayList<>(n);
        for (int i = 0; i < n; i++) buckets.add(new ArrayList<>());

        for (int num : list) {
            int idx = (int) ((long) num * n / (maxVal + 1L));
            buckets.get(idx).add(num);
        }
        for (List<Integer> bucket : buckets) Collections.sort(bucket);
        int idx = 0;
        for (List<Integer> bucket : buckets) {
            for (int num : bucket) list.set(idx++, num);
        }
    }

    // с подсчётом итераций (массив)
    public static long bucketSortArrayWithCounter(int[] arr, int maxVal) {
        if (arr == null || arr.length <= 1) return 0;
        long iterations = 0;
        int n = arr.length;
        List<Integer>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) buckets[i] = new ArrayList<>();

        for (int num : arr) {
            int idx = (int) ((long) num * n / (maxVal + 1L));
            buckets[idx].add(num);
            iterations++; // добавление в корзину
        }
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            iterations += bucket.size(); // примерная оценка операций внутри sort
        }
        int idx = 0;
        for (List<Integer> bucket : buckets) {
            for (int num : bucket) {
                arr[idx++] = num;
                iterations++;
            }
        }
        return iterations;
    }

    // с подсчётом итераций (List)
    public static long bucketSortListWithCounter(List<Integer> list, int maxVal) {
        if (list == null || list.size() <= 1) return 0;
        long iterations = 0;
        int n = list.size();
        List<List<Integer>> buckets = new ArrayList<>(n);
        for (int i = 0; i < n; i++) buckets.add(new ArrayList<>());

        for (int num : list) {
            int idx = (int) ((long) num * n / (maxVal + 1L));
            buckets.get(idx).add(num);
            iterations++;
        }
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            iterations += bucket.size();
        }
        int idx = 0;
        for (List<Integer> bucket : buckets) {
            for (int num : bucket) {
                list.set(idx++, num);
                iterations++;
            }
        }
        return iterations;
    }
}