package tests;
import algorithms.MergeSort;
import algorithms.QuickSort;
import algorithms.QuickSelect;
import metrics.Metrics;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;
public class AlgorithmTest {
    @Test
    public void testMergeSort() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int[] arr = new int[100];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = random.nextInt(1000);
            }
            int[] expected = Arrays.copyOf(arr, arr.length);
            Arrays.sort(expected);
            MergeSort.sort(arr, new Metrics());
            assertArrayEquals(expected, arr);
        }
    }
    @Test
    public void testQuickSort() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int[] arr = new int[100];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = random.nextInt(1000);
            }
            int[] expected = Arrays.copyOf(arr, arr.length);
            Arrays.sort(expected);
            QuickSort.sort(arr, new Metrics());
            assertArrayEquals(expected, arr);
        }
    }
    @Test
    public void testQuickSelect() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int[] arr = new int[100];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = random.nextInt(1000);
            }
            int k = random.nextInt(arr.length);
            int[] sorted = Arrays.copyOf(arr, arr.length);
            Arrays.sort(sorted);
            int result = QuickSelect.select(arr, k, new Metrics());
            assertEquals(sorted[k], result);
        }
    }
    @Test
    public void testEmptyArray() {
        int[] arr = {};
        MergeSort.sort(arr, new Metrics());
        assertEquals(0, arr.length);
    }
    @Test
    public void testOneElement() {
        int[] arr = {5};
        QuickSort.sort(arr, new Metrics());
        assertEquals(5, arr[0]);
    }
    @Test
    public void testSameElements() {
        int[] arr = {7, 7, 7, 7, 7};
        QuickSort.sort(arr, new Metrics());
        assertArrayEquals(new int[]{7, 7, 7, 7, 7}, arr);
    }
    @Test
    public void testSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        QuickSort.sort(arr, new Metrics());
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }
    @Test
    public void testQuickSortDepth() {
        int n = 100000;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        Metrics metrics = new Metrics();
        QuickSort.sort(arr, metrics);
        int maxAllowedDepth =
                (int) (2 * (Math.log(n) / Math.log(2)));
        assertTrue(
                metrics.getMaxDepth() <= maxAllowedDepth,
                "QuickSort recursion depth is too large: "
                        + metrics.getMaxDepth()
                        + ", allowed: "
                        + maxAllowedDepth
        );
    }
    @Test
    public void testInvalidQuickSelect() {
        int[] arr = {1, 2, 3};
        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(arr, 10, new Metrics())
        );
    }
    @Test
    public void testEmptyQuickSelect() {
        int[] arr = {};
        assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(arr, 0, new Metrics())
        );
    }
}