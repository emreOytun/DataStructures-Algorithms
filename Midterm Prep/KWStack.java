import java.util.ArrayList;
import java.util.EmptyStackException;

public class KWStack<E> {
    private ArrayList<E> list = new ArrayList<>();

    // STACK INTERFACE'INDE TEK FARK 'PUSH' METHODU 'E' DONDURUR.
    public E push(E data) {
        list.add(data);
        return data;
    }

    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.remove(list.size() - 1);
    }

    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.get(list.size() - 1);
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }
}
