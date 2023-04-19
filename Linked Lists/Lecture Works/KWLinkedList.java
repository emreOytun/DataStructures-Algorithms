import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class KWLinkedList<E> {
    
    private static class Node<E> {
        private E data;
        private Node<E> next = null;
        private Node<E> prev = null;

        private Node(E data) {
            this.data = data;
        }

    }

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    // DIKKAT: KWListIter<E> yapilmaz !!! Cunku zaten E'den baska olamaz, inner class oldugu icin.
    public ListIterator<E> listIterator(int index) {
        return new KWListIter(index);
    }
    
    public Iterator<E> iterator() {
        return listIterator(0);
    }

    public void add(int index, E newData) {
        listIterator(index).add(newData);
    }

    public E get(int index) {
        return listIterator(index).next();
    }

    public void addFirst(E item) {
        add(0, item);
    }

    public void addLast(E item) {
        add(size, item);
    }

    public E getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return head.data;
    }

    public E getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return tail.data;
    }

    public boolean remove(E obj) {
        // Iteratorler implement etmek icin:
        // 1) Objenin indeksini bul.
        // 2) Iterator ac bu indeksle ve once next sonra remove yap.

        // Liste bos mu
        if (size == 0) return false;

        // Bastan mi silinecek
        if (head.data.equals(obj)) {
            if (head == tail) {
                head = tail = null;
            }
            else {
                head = head.next;
                head.prev = null;
            }
            --size;
            return true;
        }

        // Sondan mi silinecek
        if (tail.data.equals(obj)) {
            tail = tail.prev;
            tail.next = null;
            --size;
            return true;
        }

        // Aradan silinecek
        ListIterator<E> listIterator = listIterator(1);
        while (listIterator.hasNext()) {
            if (listIterator.next().equals(obj)) {
                listIterator.previous();    // !!! Az once next() yaptigimda dondurulen objeyi silmek istiyorsam, previous yapmaliyim. !!
                listIterator.remove();
                --size;
                return true;
            }
        }
        return false;

        // Iterator'suz implement etmek daha iyi gibi, cunku bas ve son kontrolunu dogrudan yapmam daha verimli buradaki gibi.
    }


    // next 
    // hasNext 

    // previous 
    // hasPrevious

    // nextIndex
    // previousIndex

    // add -> Simdikinden oncesine ekler 
    // remove -> Previous veya next ile bir onceki alinani siler 
    // set -> Previous veya next ile bir onceki alinani siler

    public class KWListIter implements ListIterator<E> {
        // Sondaysa nextItem == null olur, index == size olur.
        // Liste bossa nextItem == null olur, index == 0 olur.
        private Node<E> nextItem;
        private Node<E> lastItemReturned;
        private int index = 0;

        public KWListIter(int index) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException(index);
            }

            lastItemReturned = null;
            
            // Iterator en sonu gosteriyorsa
            if (index == size) {
                nextItem = null;
            }
            else {
                nextItem = head; // Bastan basla, indeksteki elemana kadar git.
                for (int i = 0; i < index; ++i) {
                    nextItem = nextItem.next;
                }
            }
            this.index = index;
        }

        public boolean hasNext() {
            return nextItem != null;
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

        // ONEMLI**: (nextItem == null && size != 0) olup olmadigina bakilmali once !!
        public boolean hasPrevious() {
            return (nextItem == null && size != 0 || nextItem.prev != null); 
        }

        // ONEMLI**: nextItem == null'sa, nextItem = tail'e cekilmeli.
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            if(nextItem == null) {
                nextItem = tail;
            }
            else {
                nextItem = nextItem.prev;
            }
            lastItemReturned = nextItem;
            --index;
            return lastItemReturned.data;
        }

        public int nextIndex() {
            return index;
        }

        public int previousIndex() {
            return index-1;
        }

        // O(1)
        public void add(E newData) {
            Node<E> newNode = new Node<>(newData);
            if (head == null) {
                head = newNode;
                tail = newNode; 
                ++index; // nextItem==null'du halen null, ancak oncesine 1 eleman eklendi; index arttirilmali !!
                ++size;
                return;
            }

            if (nextItem == head) {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
                ++index; // nextItem'dan once eklendi, indeks arttirilmali !!!!
                ++size;
                return;
            }

            if (nextItem == null) {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
                ++index; // nextItem'dan once eklendi, indeks arttirilmali !!!!
                ++size;
                return;
            }

            Node<E> prevNode = nextItem.prev;
            prevNode.next = newNode;
            newNode.prev = prevNode;
            
            newNode.next = nextItem;
            nextItem.prev = newNode;
            
            ++index;
            ++size;
        }

        // O(1)
        public void remove() {
            if (lastItemReturned == null) {
                throw new IllegalStateException();
            }

            Node<E> prevNode = lastItemReturned.prev;
            Node<E> nextNode = lastItemReturned.next;

            if (prevNode == null) {
                head = nextNode;
            }
            else {
                prevNode.next = nextNode;
            }

            if (nextNode == null) {
                tail = prevNode;
            }
            else {
                nextNode.prev = prevNode;
            }
        }

        public void set(E newData) {
            if (lastItemReturned == null) {
                throw new IllegalStateException();
            }

            lastItemReturned.data = newData;
        }

    }

    public static void main(String[] args) {
        KWLinkedList<String> dll = new KWLinkedList<>();
        dll.addLast("emre");
        dll.addLast("dilek");
        dll.addFirst("ali");

        dll.remove("emre");
        dll.remove("dilek");

        dll.add(0, "necdet");
        dll.remove("ali");

        ListIterator<String> it = dll.listIterator(0);
        while (it.hasNext()) {
            String item = it.next();
            System.out.println(item);
            if (item.equals("necdet")) {
                it.previous();
                it.add("once");
                it.next();
            }
        }
        
        it = dll.listIterator(0);
        while (it.hasNext()) {
            String item = it.next();
            System.out.println(item);
            if (item.equals("necdet")) {
                it.previous();
                it.add("once");
                it.next();
            }
        }
    }
}
