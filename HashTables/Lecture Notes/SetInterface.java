import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class SetInterface {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        
        // boolean add(E element);
        set.add(3); // true
        set.add(5); // true;
        set.add(7);
        System.out.println(set.add(3)); // false

        // boolean remove(Object element);
        set.remove(3);
        set.remove(5);

        // boolean contains(Object element);
        set.contains(3);
        set.contains(7);

        // size() and isEmpty() methods:
        set.size();
        set.isEmpty();

        // Iterator<E> iterator():
        Iterator<Integer> it = set.iterator();

        set.addAll(set); // union
        set.retainAll(set); // intersection
        set.removeAll(set); // difference

    }
}
