public interface SortAlgorithm {
    public <T extends Comparable<T>> void sort(T[] table);
}
