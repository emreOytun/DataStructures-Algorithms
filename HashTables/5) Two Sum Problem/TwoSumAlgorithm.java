import java.util.HashMap;
import java.util.Map;

public class TwoSumAlgorithm {

    // O(N) time solution to find pairs from the given array that gives the sum.
    public static void twoSum(int[] arr, int sum) {
        HashMap<Integer, Integer> map = new HashMap<>();

        // O(N)
        for (int i = 0; i < arr.length; ++i) {
            if (map.get(arr[i]) == null) {
                map.put(arr[i], 1);
            }
            else {
                Integer count = map.get(arr[i]);
                map.put(arr[i], count + 1);
            }
        }

        // O(N)
        for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
            Integer difference = sum - pair.getKey();
            
            if (difference == pair.getKey()) {
                if (pair.getValue() > 1) {
                    System.out.println("[" + difference + "," + difference + "]");
                }
            }
            else if (map.get(difference) != null) {
                    System.out.println("[" + pair.getKey() + "," + difference + "]");
            }
        }

    }

}