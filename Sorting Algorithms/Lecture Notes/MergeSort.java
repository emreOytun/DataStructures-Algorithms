import java.util.ArrayList;
import java.util.Arrays;

public class MergeSort implements SortAlgorithm {
    
    public <T extends Comparable<T>> void sort(T[] arr) {
        mergeSort(arr, 0, arr.length-1);    
    }

    private <T extends Comparable<T>> void mergeSort(T[] arr, int firstIdx, int lastIdx) {
        if (firstIdx >= lastIdx) return;

        int midIdx = firstIdx + (lastIdx - firstIdx) / 2;
        mergeSort(arr, firstIdx, midIdx);
        mergeSort(arr, midIdx+1, lastIdx);
        merge(arr, firstIdx, midIdx, lastIdx);
    }

    private <T extends Comparable<T>> void merge(T[] arr, int firstIdx, int midIdx, int lastIdx) {
        int totalLen = lastIdx - firstIdx + 1;
        ArrayList<T> mergedArr = new ArrayList<>(totalLen);

        int leftIdx = firstIdx;
        int rightIdx = midIdx + 1;
        int curIdx = 0;
        while (leftIdx <= midIdx && rightIdx <= lastIdx) {
            if (arr[leftIdx].compareTo(arr[rightIdx]) < 0) {
                mergedArr.add(curIdx++, arr[leftIdx++]);
            }
            else {
                mergedArr.add(curIdx++, arr[rightIdx++]);
            }
        }
        if (leftIdx <= midIdx) {
            while (leftIdx <= midIdx) {
                mergedArr.add(curIdx++, arr[leftIdx++]);
            }
        }
        else if (rightIdx <= lastIdx) {
            while (rightIdx <= lastIdx) {
                mergedArr.add(curIdx++, arr[rightIdx++]);
            }
        }
        for (int i = 0; i < totalLen; ++i) {
            arr[firstIdx + i] = mergedArr.get(i);
        }
        
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] {1, 3, 5, 6, -1, -10, -2};
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
