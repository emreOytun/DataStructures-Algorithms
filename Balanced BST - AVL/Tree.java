public interface Tree<T> {
    void insert(T data);
    void remove(T data);
    void traversal();
    T getMin();
    T getMax();
}
