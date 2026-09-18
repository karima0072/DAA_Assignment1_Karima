package algorithms;
import metrics.Metrics;
import java.util.Random;

public class QuickSelect {
    private static Random random = new Random();
    public static int select(int[] arr, int k, Metrics metrics) {
        if (arr.length == 0) {
            throw new IllegalArgumentException("Array is empty");
        }
        if (k < 0 || k >= arr.length) {
            throw new IllegalArgumentException("Invalid k value");
        }
        return quickSelect(arr, 0, arr.length - 1, k, 1, metrics);
    }
    private static int quickSelect(int[] arr, int left, int right, int k, int depth, Metrics metrics) {
        metrics.updateDepth(depth);

        if (left == right) {
            return arr[left];
        }

        int pivotIndex = left + random.nextInt(right - left + 1);
        int pivot = arr[pivotIndex];

        int[] result = partition(arr, left, right, pivot, metrics);

        if (k < result[0]) {
            return quickSelect(arr, left, result[0] - 1, k, depth + 1, metrics);
        } else if (k > result[1]) {
            return quickSelect(arr, result[1] + 1, right, k, depth + 1, metrics);
        } else {
            return arr[k];
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