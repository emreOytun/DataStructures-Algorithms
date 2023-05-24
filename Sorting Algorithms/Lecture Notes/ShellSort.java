import java.util.Arrays;

public class ShellSort implements SortAlgorithm {
    public <T extends Comparable<T>> void sort(T[] arr) {
        // usually the gap = number of items / 2
        // when the gap is 1, this is the standart insertion sort
        for (int gap = arr.length/2; gap > 0; gap/=2) {
            // insertion-sort yapilmasi gereken ilk elemanin idx=gap'tir. Bundan sonraki her eleman icin gap'e gore insertion yapilmali.
            for (int i = gap; i < arr.length; ++i) {
                T temp = arr[i];
                int j = i-gap;
                while(j>=0 && temp.compareTo(arr[j]) < 0) {
                    arr[j+gap] = arr[j];
                    j -= gap;
                }
                arr[j+gap] = temp;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] {1, 3, -2, 0, -4};
        ShellSort shellSort = new ShellSort();
        shellSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
