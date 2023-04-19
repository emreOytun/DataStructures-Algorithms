import java.util.AbstractList;
import java.util.List;

/*
 * AbstractList<E> implement edildigidne minimum implement edilmesi gerekenler:
 * E get(int index)
 * int size()
 * void add(int index, E obj) 
 * E remove(int index)
 * boolean remove(Object obj)
 * E set(int index, E item)
 * 
 * AbstractList<E> abstract class'i asagidakileri implement ediyor:
 * boolean add(E obj)
 * contains/containsAll/toArray ...
 * 
 */
public class KWSingleList<E> extends AbstractList<E> implements List<E> {
    
    private static class Node<E> {
        private E data;
        private Node<E> next = null;

        public Node(E data) {
            this.data = data;
        }
    }

    private Node<E> head = null;
    private int size = 0;

    private Node<E> getIndex(int index) {
        String str = new String();
        Node<E> curNode = head;
        for (int i = 0; i < index; ++i) {
            curNode = curNode.next;
        }
        return curNode;
    }

    public void add(int index, E item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> curNode = getIndex(index-1);
        Node<E> newNode = new Node<>(item);
        newNode.next = curNode.next;
        curNode.next = newNode;
        ++size;
    }

    public E remove(int index) {
        if (index < 0 || index >=size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> curNode = getIndex(index-1);
        E result = curNode.next.data;
        curNode.next = curNode.next.next;
        --size;
        return result;
    }

    public E set(int index, E obj) {
        if (index < 0 || index >=size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> curNode = getIndex(index);
        E result = curNode.data;
        curNode.data = obj;
        return result;     
    }

    public E get(int index) {
        if (index < 0 || index >=size) {
            throw new IndexOutOfBoundsException();
        }
        return getIndex(index).data;
    }

    public int size() {
        return size;
    }
}
