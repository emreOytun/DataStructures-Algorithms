import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] arr = new int[] {-3, 3, -6, 5, 40, 10};
        Quicksort quicksort = new Quicksort(arr);
        System.out.println(Arrays.toString(quicksort.sort()));
    }
}
