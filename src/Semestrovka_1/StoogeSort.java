package Semestrovka_1;


import java.util.List;

public class StoogeSort {

    // Для массива int[] (счётчик итераций)
    public static void sort(int[] arr, Counter counter) {
        stoogeSort(arr, 0, arr.length - 1, counter);
    }

    private static void stoogeSort(int[] a, int i, int j, Counter cnt) {
        if (a[i] > a[j]) {
            int t = a[i]; a[i] = a[j]; a[j] = t;
            cnt.inc();
        }
        if (j - i >= 2) {
            int t = (j - i + 1) / 3;
            stoogeSort(a, i, j - t, cnt);
            stoogeSort(a, i + t, j, cnt);
            stoogeSort(a, i, j - t, cnt);
            cnt.inc(3);
        }
    }

    // Для List<Integer>
    public static void sort(List<Integer> list, Counter counter) {
        stoogeSort(list, 0, list.size() - 1, counter);
    }

    private static void stoogeSort(List<Integer> list, int i, int j, Counter cnt) {
        if (list.get(i) > list.get(j)) {
            int t = list.get(i);
            list.set(i, list.get(j));
            list.set(j, t);
            cnt.inc();
        }
        if (j - i >= 2) {
            int t = (j - i + 1) / 3;
            stoogeSort(list, i, j - t, cnt);
            stoogeSort(list, i + t, j, cnt);
            stoogeSort(list, i, j - t, cnt);
            cnt.inc(3);
        }
    }
}