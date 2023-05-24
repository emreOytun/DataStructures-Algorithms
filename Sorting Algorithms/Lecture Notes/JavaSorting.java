import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JavaSorting {
    public static void main(String[] args) {
        int[] arr = null;
        
        // Arrays.sort(arr)
        Arrays.sort(arr);

        // Arrays.sort(arr, from_i(inclusive), to_i(exclusive))
        Arrays.sort(arr, 0, 3);

        // Arrays.sort(arr, comparator)
        Arrays.sort(arr, (l, r) -> Integer.compare(l, r));

        // Arrays.sort(arr, from_i, to_i, comparator)
        Arrays.sort(arr, 0, 3, null);

        List<Integer> list = new ArrayList<>();
        
        // Collections.sort(collection)
        Collections.sort(list);
        
        // Collections.sort(collection, comparator)
        Collections.sort(list, null);
        
        // Arrays.equals(arr1, arr2)
        // Arrays.asList(arr) -> Array'i list'e cevirir.
        
        // Collections.reverse(collection)
        // Collections.max(collection)
        // Collections.min(collection)
    }
}