import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Integer[] arr = new Integer[] {1, 3, -10, 99, 5, -20, 1, 0};
        Mergesort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
