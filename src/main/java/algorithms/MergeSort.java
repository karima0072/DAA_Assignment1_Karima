package algorithms;
import metrics.Metrics;

public class MergeSort {
    private static final int CUTOFF = 15;
    public static void sort(int[] arr, Metrics metrics) {
        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1, 1, metrics);
    }
    private static void mergeSort(int[] arr, int[] temp, int left, int right, int depth, Metrics metrics) {
        metrics.updateDepth(depth);
        if (left >= right) {
            return;
        }
        if (right - left + 1 <= CUTOFF) {
            insertionSort(arr, left, right, metrics);
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(arr, temp, left, mid, depth + 1, metrics);
        mergeSort(arr, temp, mid + 1, right, depth + 1, metrics);
        merge(arr, temp, left, mid, right, metrics);
    }
    private static void merge(int[] arr, int[] temp, int left, int mid, int right, Metrics metrics) {
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            metrics.addComparison();
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            arr[k] = temp[i];
            i++;
            k++;
        }
        while (j <= right) {
            arr[k] = temp[j];
            j++;
            k++;
        }
    }
    private static void insertionSort(int[] arr, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int value = arr[i];
            int j = i - 1;
            while (j >= left) {
                metrics.addComparison();
                if (arr[j] > value) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = value;
        }
    }
}
