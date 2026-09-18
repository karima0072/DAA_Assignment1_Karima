package benchmark;
import algorithms.MergeSort;
import algorithms.QuickSort;
import algorithms.QuickSelect;
import metrics.Metrics;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Benchmark {
    private static Random random = new Random();
    public static void main(String[] args) throws IOException {
        int[] sizes = {1000,10000,100000,1000000};
        String[] types = {"random","sorted","duplicates"};
        FileWriter file = new FileWriter("results.csv");
        file.write("algorithm,input,n,time_ms,comparisons,max_depth\n");
        for(int n : sizes){
            for(String type : types){
                int[] array = createArray(n,type);
                testMergeSort(array,type,file);
                testQuickSort(array,type,file);
                testQuickSelect(array,type,file);
            }
        }
        file.close();
        System.out.println("Benchmark finished");
    }

    private static int[] createArray(int n,String type){
        int[] arr = new int[n];
        if(type.equals("random")){
            for(int i=0;i<n;i++){
                arr[i]=random.nextInt(100000);
            }
        }
        if(type.equals("sorted")){
            for(int i=0;i<n;i++){
                arr[i]=i;
            }
        }

        if(type.equals("duplicates")){
            for(int i=0;i<n;i++){
                arr[i]=random.nextInt(10);
            }
        }

        return arr;
    }
    private static void testMergeSort(int[] original,String type,FileWriter file)throws IOException{
        double[] times = new double[5];
        long comparisons = 0;
        int depth = 0;

        for(int i=0;i<5;i++){
            int[] arr = Arrays.copyOf(original,original.length);
            Metrics metrics = new Metrics();

            metrics.startTimer();
            MergeSort.sort(arr,metrics);
            metrics.stopTimer();

            times[i]=metrics.getTimeMillis();
            comparisons+=metrics.getComparisons();
            depth=Math.max(depth,metrics.getMaxDepth());
        }

        Arrays.sort(times);
        double median=times[2];
        file.write("MergeSort,"+type+","+original.length+","+median+","+comparisons/5+","+depth+"\n");
    }

    private static void testQuickSort(int[] original,String type,FileWriter file)throws IOException{
        double[] times = new double[5];
        long comparisons = 0;
        int depth = 0;
        for(int i=0;i<5;i++){
            int[] arr = Arrays.copyOf(original,original.length);
            Metrics metrics = new Metrics();

            metrics.startTimer();
            QuickSort.sort(arr,metrics);
            metrics.stopTimer();

            times[i]=metrics.getTimeMillis();
            comparisons+=metrics.getComparisons();
            depth=Math.max(depth,metrics.getMaxDepth());
        }

        Arrays.sort(times);
        double median=times[2];

        file.write("QuickSort,"+type+","+original.length+","+median+","+comparisons/5+","+depth+"\n");
    }

    private static void testQuickSelect(int[] original,String type,FileWriter file)throws IOException{
        double[] times = new double[5];
        long comparisons = 0;
        int depth = 0;

        for(int i=0;i<5;i++){
            int[] arr = Arrays.copyOf(original,original.length);
            Metrics metrics = new Metrics();

            int k=arr.length/2;

            metrics.startTimer();
            QuickSelect.select(arr,k,metrics);
            metrics.stopTimer();

            times[i]=metrics.getTimeMillis();
            comparisons+=metrics.getComparisons();
            depth=Math.max(depth,metrics.getMaxDepth());
        }

        Arrays.sort(times);
        double median=times[2];
        file.write("QuickSelect,"+type+","+original.length+","+median+","+comparisons/5+","+depth+"\n");
    }
}