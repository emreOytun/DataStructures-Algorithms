import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Queue;

public class KWPriorityQueue<E> extends AbstractQueue<E> implements Queue<E> {

    private ArrayList<E> data = new ArrayList<>();
    private Comparator<E> comparator = null;

    KWPriorityQueue() {}
    KWPriorityQueue(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public boolean offer(E item) {
        data.add(item);
        heapifyUp(data.size()-1);
        return true;
    }

    @Override
    public E peek() {
        if (data.size() == 0) return null;
        E result = data.get(0);
        swap(0, data.size()-1);
        data.remove(data.size()-1);
        heapifyDown(0);
        return result;
    }

    @Override
    public E poll() {
        if (data.size() == 0) return null;
        return data.get(0);
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();  
    }

    private void heapifyUp(int index) {
        boolean isDone = false;
        while (!isDone && index > 0) {
            int parent = (int) Math.floor(index / 2);
            if (compare(data.get(index), data.get(parent)) < 0) {
                swap(index, parent);
                index = parent;
            }
            else {
                isDone = true;
            }
        }
    }
    
    private void heapifyDown(int index) {
        boolean isDone = false;
        while (!isDone && index > data.size()-1) {
            int child1 = 2*index + 1;
            int child2 = child1 + 1;
            int min_i = index;
            if (child1 < data.size() && compare(data.get(child1), data.get(min_i)) < 0) {
                min_i = child1;
            }
            if (child2 < data.size() && compare(data.get(child2), data.get(min_i)) < 0) {
                min_i = child2;
            } 
            if (min_i == index) isDone = true;
            else {
                swap(index, min_i);
                index = min_i;
            }
        }
    }
    
    private void swap(int i1, int i2) {
        E temp = data.get(i1);
        data.set(i1,data.get(i2));
        data.set(i2, temp);
    }

    // First check if there is a comparator defined before.
    // If it is, then compare it using comparator.compare(left, right) method.
    // Otherwise, cast one of the items to Comparable<E>; then use the compareTo method. 
    // NOTE**: If you do not cast it to Comparable<E>, then the compiler sees that the item does not have that method.
    private int compare(E left , E right) {
        if (comparator != null) {
            return comparator.compare(left, right);
        }
        return ((Comparable<E>)left).compareTo(right);
    }
}