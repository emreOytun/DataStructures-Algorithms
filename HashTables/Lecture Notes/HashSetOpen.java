public class HashSetOpen<E> {
    private HashTableOpen<E,E> map = new HashTableOpen<>();

    public boolean add(E item) {
        if (map.get(item) != null) {
            return false;
        }
        map.put(item, item);
        return true;
    }

    public boolean remove(E item) {
        return map.remove(item) == null ? false : true;
    }

    public boolean contains(E item) {
        return map.get(item) == null ? false : true;
    }
}