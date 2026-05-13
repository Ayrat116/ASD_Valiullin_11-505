package Semestrovka_1;

import java.util.List;

public class CombSort {

    // Для массива int[]
    public static void sort(int[] arr, Counter counter) {
        int n = arr.length;
        int gap = n;
        boolean swapped = true;
        final float shrink = 1.3f;
        while (gap > 1 || swapped) {
            if (gap > 1) gap = (int)(gap / shrink);
            swapped = false;
            for (int i = 0; i + gap < n; i++) {
                if (arr[i] > arr[i + gap]) {
                    int t = arr[i]; arr[i] = arr[i + gap]; arr[i + gap] = t;
                    swapped = true;
                    counter.inc();
                }
                counter.inc(); // каждое сравнение
            }
        }
    }

    // Для List<Integer>
    public static void sort(List<Integer> list, Counter counter) {
        int n = list.size();
        int gap = n;
        boolean swapped = true;
        final float shrink = 1.3f;
        while (gap > 1 || swapped) {
            if (gap > 1) gap = (int)(gap / shrink);
            swapped = false;
            for (int i = 0; i + gap < n; i++) {
                if (list.get(i) > list.get(i + gap)) {
                    int t = list.get(i);
                    list.set(i, list.get(i + gap));
                    list.set(i + gap, t);
                    swapped = true;
                    counter.inc();
                }
                counter.inc();
            }
        }
    }
}