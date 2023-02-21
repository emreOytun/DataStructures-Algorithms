import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Integer[] arr = new Integer[] {1, -3, 20, -7, 2, 22, 10, 13};
        Heapsort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
