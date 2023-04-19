import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class ListStack<E> {

    private List<E> data;

    public ListStack() {
        data = new ArrayList<>();
    }

    public E push(E obj) {
        data.add(obj);
        return obj;
    }

    public E peek() throws EmptyStackException {
        if (empty()) {
            throw new EmptyStackException();
        }
        return data.get(data.size() - 1);
    }

    public E pop() throws EmptyStackException {
        if (empty()) {
            throw new EmptyStackException();
        }
        return data.remove(data.size() - 1);
    }

    public boolean empty() {
        return data.size() == 0;
    }



}