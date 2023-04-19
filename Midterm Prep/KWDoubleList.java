import java.util.AbstractList;
import java.util.ListIterator;

/**
 * AbstractQueue'nun implement etmedigi Queue<E> interface'i icin gerekenler:
 * boolean offer()
 * E poll()
 * E peek()
 * int size()
 * Iterator<E> iterator()
 * 
 * Deque interface'inden gelenler:
 * void addFirst(), void addLast() 
 * boolean offerFirst(), offerLast()
 * E peekFirst(), peekLast()
 * E pollFirst(), pollLast()
 * E getFirst(), getLast()
 * 
 */
public class KWDoubleList<E> extends AbstractList<E> {
    
    private static class Node<E> {
        private E data;
        private Node<E> next = null;
        private Node<E> previous = null;

        public Node(E data) {
            this.data = data;
        }
    }

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private Node<E> getNode(int index) {
        Node<E> curNode = head;
        for (int i = 0; i < index; ++i) {
            curNode = curNode.next;
        }
        return curNode;
    }

    @Override
    public void add(int index, E item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> newNode = new Node<>(item);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } 
        else if (index == 0) {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        }
        else if (index == size) {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        else {
            Node<E> curNode = getNode(index-1);
            Node<E> nextNode = curNode.next;
            curNode.next = newNode;
            newNode.previous = curNode;
            newNode.next = nextNode;
            nextNode.previous = newNode;
        }
        ++size;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E result = null;
        if (index == 0) {
            result = head.data;
            if (size == 1) {
                head = null;
                tail = null;
            }
            else {
                head.next.previous = null;
                head = head.next;
            }
        }
        else if (index == size-1) {
            result = tail.data;
            tail = tail.previous;
            tail.next = null;
        }
        else {
            Node<E> curNode = getNode(index);
            result = curNode.data;
            if (curNode.previous == null) {
                head = curNode.next;
            }
            else {
                curNode.previous.next = curNode.next;
            }

            if (curNode.next == null) {
                tail = tail.previous;
            }
            else {
                curNode.next.previous = curNode.previous;
            }
        }
        --size;
        return result;
    }

    @Override
    public E set(int index, E obj) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> curNode = getNode(index);
        E result = curNode.data;
        curNode.data = obj;
        return result;
    }

    @Override
    public E get(int index) {
        return getNode(index).data;
    }

    @Override
    public int size() {
        return size;
    }

    private class KWDoubleListIter implements ListIterator<E> {
        private Node<E> nextItem;
        private Node<E> lastItemReturned = null;
        private int index;

        public KWDoubleListIter(int index) {
            this.index = index;
            nextItem = getNode(index);
        }

        // next,hasNext
        // previous,hasPrevious
        // void add,remove,set ...

    }

}
