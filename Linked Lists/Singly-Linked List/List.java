public interface List<T> {
    void insertBeginning(T data);
    void insertEnd(T data);
    void remove(T data);
    void traverse();
    int size();
}
