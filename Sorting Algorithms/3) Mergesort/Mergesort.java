import java.util.ArrayList;

public class Mergesort {

    public static <T extends Comparable> void sort(T[] arr) {
        mergeSort(arr, 0, arr.length-1);
    }

    // O(NlogN) = O(logN) for diving subarrays * O(N) for merging subarray each time 
    private static <T extends Comparable> void mergeSort(T[] arr, int firstIdx, int lastIdx) {
        // If there is 1 element then just return.
        if (firstIdx >= lastIdx) return;
        
        int midIdx = firstIdx + (lastIdx - firstIdx) / 2;
        mergeSort(arr, firstIdx, midIdx);
        mergeSort(arr, midIdx+1, lastIdx);
        
        // Merge the subarrays.
        merge(arr, firstIdx, midIdx, lastIdx);
    }

    // O(N) space
    // O(N) time (O(N) for merging + O(N) for copying into original array)
    private static <T extends Comparable> void merge(T[] arr, int firstIdx, int midIdx, int lastIdx) {
        // Find the total length of two subarrays, create a new array with it.
        int totalLen = lastIdx - firstIdx + 1;
        ArrayList<T> mergedArr = new ArrayList<>(totalLen);
        
        // O(N)
        int idx1 = firstIdx;
        int idx2 = midIdx+1;
        int i = 0;
        while (idx1 <= midIdx && idx2 <= lastIdx) {
            // If the elements in the 2nd subarray is less than the element in the first one,
            // put these elements into the new merged array.
            if (arr[idx2].compareTo(arr[idx1]) < 0) {
                mergedArr.add(i++, arr[idx2++]);
            }
            
            // Otherwise put the element in the 1st subarray into the merged array.
            else {
                mergedArr.add(i++, arr[idx1++]);
            }
        }

        // Check if one of the subarrays is not completed yet.
        if (idx1 <= midIdx) {
            while (idx1 <= midIdx) {
                mergedArr.add(i++, arr[idx1++]);
            }
        }
        else if (idx2 <= lastIdx) {
            while (idx2 <= lastIdx) {
                mergedArr.add(i++, arr[idx2++]);
            }
        }
    
        // Copy the merged array into the original array 
        // O(N)
        for (int j = 0; j < mergedArr.size(); ++j) {
            arr[firstIdx + j] = mergedArr.get(j);
        }
    }

}