import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/*
 * AbstractSequentialList extend edildiginde implement edilmesi gerekenler:
 * size
 * listIterator
 * 
 * AbstractSequentilList, AbstractList'in aksine add,remove,set,get metodlarini implement etmistir.
 */
public class KWCircularLinkedList<E> extends AbstractSequentialList<E> { 
    
    private static class Node<E> {
        private E data;
        private Node<E> next = null;
    
        public Node(E data) {
            this.data = data;
        }
    }

    private Node<E> head;
    private int size;

    public int size() {
        return size;
    }

    public ListIterator<E> listIterator(int index) {
        return new CircularLLIter(index);
    }

    private Node<E> getNode(int index) {
        Node<E> curNode = head;
        for (int i = 0; i < index; ++i) {
            curNode = curNode.next;
        }
        return curNode;
    }

    private class CircularLLIter implements ListIterator<E> {
        private Node<E> nextItem;
        private Node<E> lastItemReturned = null;
        private int index;

        public CircularLLIter(int index) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException();
            }
            this.index = index;
            nextItem = getNode(index);
        }

        public int nextIndex() {
            return index;
        }

        public int previousIndex() {
            return index - 1;
        }
        
        public boolean hasNext() {
            return index < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            ++index;
            return lastItemReturned.data;
        }

        public boolean hasPrevious() {
            throw new UnsupportedOperationException();
        }

        public E previous() {
            throw new UnsupportedOperationException();
        }

        public void add(E item) {
            Node<E> newNode = new Node<>(item);
            if (head == nextItem) {
                newNode.next = head;
                head = newNode;
            }
            else {
                Node<E> prevNode = getNode(index-1);
                if (prevNode.next == null) newNode.next = head;
                else newNode.next = prevNode.next;
                prevNode.next = newNode;
            }
            ++index;
            ++size;
            lastItemReturned = null;
        }

        public void remove() {
            if (lastItemReturned == null) {
                throw new IllegalStateException();
            }
            if (head == nextItem) {
                head = head.next;
            }
            else {
                Node<E> prevNode = getNode(index-1);
                prevNode.next = nextItem.next;
            }
            
            if (lastItemReturned == nextItem) {
                nextItem = nextItem.next;
            }
            else {
                --index;
            }
            --size;
            lastItemReturned = null;
        }

        public void set(E data) {
            if (lastItemReturned == null) {
                throw new IllegalStateException();
            }
            lastItemReturned.data = data;
        }
    }
}
