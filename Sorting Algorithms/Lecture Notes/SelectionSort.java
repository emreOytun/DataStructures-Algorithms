public class SelectionSort implements SortAlgorithm {

    // O(N^2) -> Comparisions(O(n^2)) + Swaps(O(n))
    @Override
    public <T extends Comparable<T>> void sort(T[] table) {
        int n = table.length;
        for (int i = 0; i < n - 1; ++i) {
            int minPos = i;
            for (int j = i + 1; j < n; ++j) {
                if (table[j].compareTo(table[minPos]) < 0) {
                    minPos = j;
                }
            }
            T temp = table[i];
            table[i] = table[minPos];
            table[minPos] = temp;
        }
    }
    
}
