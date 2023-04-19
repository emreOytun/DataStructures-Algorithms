import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class OrderedLL<E extends Comparable<E>> {
    private LinkedList<E> ll;

    public void add(E newData) {
        ListIterator<E> iter = ll.listIterator();
        while (iter.hasNext()) {
            if (newData.compareTo(iter.next()) < 0) {
                iter.previous();
                iter.add(newData);
                return;
            }
        }
        iter.add(newData); // Araya girmeyecekse sona ekle.
    }

    public E get(int index) {
        return ll.get(index);
    }

    public int size() {
        return ll.size();
    }

    public boolean remove(E data) {
        return ll.remove(data);
    }

    public Iterator iterator() {
        return ll.iterator();
    }

}
