import java.util.Arrays;

public class MergeSortV2 implements SortAlgorithm{
    
    public <T extends Comparable<T>> void sort(T[] arr) {
        if (arr.length > 1) {
            int halfSize = arr.length / 2;
            T[] leftArr = (T[]) new Comparable[halfSize];   // Arrays.copyOfRange(arr, fromIdx(inclusive), toIdx(exclusive)) kullanilabilir.
            T[] rightArr = (T[]) new Comparable[arr.length - halfSize];
            for (int i = 0; i < halfSize; ++i) {
                leftArr[i] = arr[i];
            }
            for (int i = halfSize; i < arr.length; ++i) {
                rightArr[i] = arr[i];
            } 
            
            sort(leftArr);
            sort(rightArr);
            merge(arr, leftArr, rightArr);
        }
    }

    private <T extends Comparable<T>> void merge(T[] output, T[] left, T[] right) {
        int leftIdx = 0;
        int rightIdx = 0;
        int i = 0;
        while (leftIdx < left.length && rightIdx < right.length) {
            if (left[leftIdx].compareTo(right[rightIdx]) < 0) {
                output[i++] = left[leftIdx++];
            }
            else {
                output[i++] = right[rightIdx++];
            }
        }
        while (leftIdx < left.length) {
            output[i++] = left[leftIdx++];
        }
        while (rightIdx < right.length) {
            output[i++] = right[rightIdx++];
        }
    } 
}
