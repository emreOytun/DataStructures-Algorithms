// Eleman yoksa -> NoSuchElementException
// Index hataliysa -> IndexOutOfBoundsException
public class SingleLL<E> { 
    
    // NOT**: Static inner class, disardaki class'a reference tutmaz. Bunlara nested class'ta denir.
    // Not**: Genel olarak inner Node class'i, constructor'i ve degiskenleri private'tir. Sadece bu class kullanabilir.
    private static class Node<E> {

        private E data;
        private Node<E> next;

        private Node(E data) {
            this.data = data;
            next = null;
        }

        private Node(E data, Node<E> nodeRef) {
            this.data = data;
            next = nodeRef;
        }
    }

    private Node<E> head = null;
    private int size = 0;

    // O(1)
    private void addFirst(E item) {
        Node<E> newNode = new Node<>(item, head);
        head = newNode;
        ++size;
    }

    // O(1)
    private E removeFirst() {
        // Liste bos mu diye kontrol et.
        if (head == null) return null;
        E data = head.data;
        head = head.next;
        --size;
        return data;
    }

    // O(1)
    private void addAfter(Node<E> node, E item) {
        Node<E> newNode = new Node<>(item, node.next);
        node.next = newNode;
        ++size;
    }

    // O(1)
    private E removeAfter(Node<E> node) {
        Node<E> removingNode = node.next;
        node.next = removingNode.next;
        --size;
        return removingNode.data;
    }  

    // O(N)
    private Node<E> getNode(int index) {
        Node<E> curNode = head;
        int idx;
        for (idx = 0; idx < index && curNode != null; ++idx) {
            curNode = curNode.next;
        }
        
        return curNode;
    }

    private Node<E> getNode(E element) {
        Node<E> curNode = head;
        Node<E> result = null;
        while (curNode != null) {
            if (curNode.data.equals(element)) {
                return curNode;
            }
        }
        return curNode;
    }

    // Traverse et. 
    // O(N)
    @Override
    public String toString() {
        Node<E> curNode = head;
        StringBuilder result = new StringBuilder();
        
        while (curNode != null) {
            result.append(curNode.data);
            curNode = curNode.next;
            if (curNode != null) {
                result.append("=>");
            }
        }
        return result.toString();
    }

    // O(N)
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        
        Node<E> node = getNode(index);
        return node.data;
    }

    // O(N)
    public void add(int index, E newElement) {
        // Yeni eleman sona eklenebilir.
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }

        // Basa mi eklenicek.
        if (index == 0) {
            addFirst(newElement);
        }

        // Araya veya sona eklenecekse, index'ten onceki node'u bul. Zaten index'i kontrol ettik.
        else {
            Node<E> node = getNode(index-1);
            addAfter(node, newElement);
        }
    }

    // Adds the new element at the end of the list.
    // O(N)
    public boolean add(E item) {
        add(size, item);  
        return true;
    }

    // O(N)
    public E set(int index, E newData) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.valueOf(index));
        }

        Node<E> node = getNode(index);
        E oldData = node.data;
        node.data = newData;
        return oldData;
    }

    // O(N)
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(Integer.valueOf(index));
        }

        // Bastan mi silinecek
        if (index == 0) {
            return removeFirst();
        }

        Node<E> prevNode = getNode(index-1);
        return removeAfter(prevNode);
    }

    // O(N)
    public boolean remove(E element) {
        Node<E> prev = null;
        Node<E> cur = head;

        // Bastan mi silinecek
        if (head.data.equals(element)) {
            head = head.next;
            --size;
            return true;
        }

        while (cur != null) {
            if (cur.data.equals(element)) {
                prev.next = cur.next;        
                --size;        
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    // O(N)
    public int indexOf(E element) {
        Node<E> curNode = head;
        int idx = -1;
        boolean isFound = false;

        for (int i = 0; i < size && !isFound; ++i) {
            if (curNode.data.equals(element)) {
                isFound = true;
                idx = i;
            }
            else {
                curNode = curNode.next;
            }
        }
        return idx;
    }

}