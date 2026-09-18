package algorithms;
import metrics.Metrics;
import java.util.Random;

public class QuickSort {
    private static Random random = new Random();
    public static void sort(int[] arr, Metrics metrics) {
        quickSort(arr, 0, arr.length - 1, 1, metrics);
    }
    private static void quickSort(int[] arr, int left, int right, int depth, Metrics metrics) {
        while (left < right) {
            metrics.updateDepth(depth);
            int pivotIndex = left + random.nextInt(right - left + 1);
            int pivot = arr[pivotIndex];
            int[] result = partition(arr, left, right, pivot, metrics);
            if (result[0] - left < right - result[1]) {
                quickSort(arr, left, result[0] - 1, depth + 1, metrics);
                left = result[1] + 1;
            } else {
                quickSort(arr, result[1] + 1, right, depth + 1, metrics);
                right = result[0] - 1;
            }
        }
    }

    private static int[] partition(int[] arr, int left, int right, int pivot, Metrics metrics) {
        int low = left;
        int current = left;
        int high = right;
        while (current <= high) {
            metrics.addComparison();
            if (arr[current] < pivot) {
                swap(arr, low, current);
                low++;
                current++;
            } else if (arr[current] > pivot) {
                swap(arr, current, high);
                high--;
            } else {
                current++;
            }
        }
        return new int[]{low, high};
    }
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}