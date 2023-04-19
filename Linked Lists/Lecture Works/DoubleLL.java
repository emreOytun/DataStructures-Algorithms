// DIKKAT: DLL'te next'in prev'unu set etmeden once, her zaman next var mi diye check etmeyi dusun? 
// ORN: Sondan sildigim bir node icin node.next.prev exception firlatir gibi.
public class DoubleLL<E> {
    
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

    private Node<E> getNode(int index) {
        Node<E> curNode = head;
        for (int i = 0; i < index; ++i) {
            curNode = curNode.next;
        }
        return curNode;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }

        return getNode(index).data;
    }

    public void add(int index, E newData) {
        // Basa, sona ve araya eklenebilir.
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(index);
        }

        Node<E> newNode = new Node<>(newData);
        
        // Liste bos mu
        // Liste bossa (head'in disinda tail'de guncellenecek, SLL'te buna gerek yok ayrica bakmaya)
        if (size == 0) {
            head = newNode;
            tail = newNode;
            ++size;
            return;
            // TODO
            //newNode.prev = newNode;
            //newNode.next = newNode;
        }

        // Basa mi eklenecek
        if (index == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode; // update head
            ++size;
            return;
            // TODO
           // newNode.prev = tail;
           // tail.next = newNode;
        
        }

        // Sona mi eklenecek 
        // NOT**: Index verildigi icin dogrudan sona ekleme kontrolu yapilmali ki O(1)'da calissin. SortedDLL yapsaydik o zaman alttaki gibi nereye yerlesecegine bakacaktik belki, ki onda bile dogrudan sona mi yerlesecegine bakariz ki O(1)'da calissin bu durumda.
        if (index == size) {
            Node<E> prev = tail;
            prev.next = newNode;
            newNode.prev = prev;
            tail = newNode;
            ++size;
            return;
        }

        // Araya ekleme
        else {
            Node<E> prevNode = getNode(index-1); // Sona ekleme durumu olabileceginden bir onceki node'u al.
            Node<E> nextNode = prevNode.next;

            prevNode.next = newNode;
            newNode.prev = prevNode;

            newNode.next = nextNode;
            nextNode.prev = newNode;
        }
        
        // Araya ekleme (Hem de sona mi eklenecek kontrol ediyor ayni zamanda)
        /*
        else {
            Node<E> prevNode = getNode(index-1); // Sona ekleme durumu olabileceginden bir onceki node'u al.
            Node<E> nextNode = prevNode.next;

            prevNode.next = newNode;
            newNode.prev = prevNode;

            newNode.next = nextNode;
            if (nextNode == null) { // Sona ekleniyorsa tail'i guncelle.
                tail = newNode;

                // TODO
             //   newNode.next = head;
               // head.prev = newNode;
            }
            else {  // Araya ekleme
                nextNode.prev = newNode;
            }
        }
        */

        ++size; // Size'i arttirmayi unutma
    }

    // Sona ekleme DLL'te tail kullanarak O(1) olarak yapilmali.
    public boolean add(E newData) {
        Node<E> newNode = new Node<>(newData);
        if (tail == null) {    // Liste bossa tail==null'dur. Check et.
            head = newNode;     // Liste bossa head ve tail'i guncelle !!
            tail = newNode;

            // TODO
          //  newNode.next = newNode;
           // newNode.prev = newNode;
        }
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode; // Sone ekleyince tail'i guncelle !!

            // TODO
        //    newNode.next = head;
          //  head.prev = newNode;
        }
        ++size; // Size'i arttirmayi unutma.
        return true;
    }

    // O(N)
    public E set(int index, E newData) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
        
        Node<E> node = getNode(index);
        E oldData = node.data;
        node.data = newData;
        return oldData;
    }

    // NOT**: DLL'TE SILERKEN AYNI ANDA HEM BASTAN MI SILINECEK HEM DE SONDAN MI SILINECEGININ KONTROLU YAPILMALI !!
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }  

        // Bastan mi cikicak
        if (index == 0) {
            E oldData = head.data;

            // head==tail mi (1 eleman mi var)
            if (head == tail) {
                head = null;
                tail = null;
            }
            else {
                Node<E> nextNode = head.next;
                nextNode.prev = null;   // !! Head ve tail'i set ederken ve herhangi bir next set ettiginde previous'ta set edilmeli !!
                head = nextNode;
            }
            --size;
            return oldData;
        }

        // Sondan mi cikicak (en az 2 eleman var)
        if (index == size-1) {
            Node<E> prevNode = tail.prev;
            prevNode.next = null;
            tail = prevNode;
            --size;
            return prevNode.data;
        }

        // Aradan cikacakq
        Node<E> node = getNode(index); // Silinecek node'u bul.
        Node<E> prevNode = node.prev;
        Node<E> nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        --size;
        return node.data;

        /* 
        // Bastan siliyor olabiliriz, kontrol et.
        if (prevNode == null) {
            head = nextNode;
        }
        else {
            prevNode.next = nextNode;
        }

        // Sondan siliyor olabiliriz kontrol et. (Hem bastan hem sondan da siliyor olabiliriz ayni zamanda)
        if (nextNode == null) {
            tail = node.prev;
        }
        else {
            nextNode.prev = prevNode;
        }
        

        E oldData = node.data;
        node = null;
        return oldData;
        */
    }

    public boolean remove(E element) {
        Node<E> curNode = head;

        // Bastan mi silinecek
        if (head.data.equals(element)) {
            if (head == tail) {
                head = null;
                tail = null;
            }
            else {
                head = head.next;
                head.prev = null;
            }
            --size;
            return true;
        }

        // Sondan mi silinecek
        if (tail.data.equals(element)) {
            tail.prev.next = null;
            tail = tail.prev;
            --size;
            return true;
        }

        // Find the node first.
        boolean isFound = false;
        while (curNode != null && !isFound) {
            if (curNode.data.equals(element)) {
                isFound = true;
            }
            else {
                curNode = curNode.next;
            }
        }

        // Random node silme aradan
        if (isFound) {
            Node<E> prevNode = curNode.prev;
            Node<E> nextNode = curNode.next;

            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            --size;
            /* 
            // Bastan siliyor olabiliriz, kontrol et.
            if (prevNode == null) {
                head = nextNode;
            }
            else {
                prevNode.next = nextNode;
            }

            // Sondan siliyor olabiliriz kontrol et. (Hem bastan hem sondan da siliyor olabiliriz ayni zamanda)
            if (nextNode == null) {
                tail = prevNode;
            }
            else {
                nextNode.prev = prevNode;
            }
            */
        }

        return isFound;
    }   

    public static void main(String[] args) {
        DoubleLL<String> dll = new DoubleLL<>();
        dll.add("emre");
        dll.add("ali");
        dll.add("necdeet");
        System.out.println(dll);

        dll.add(0, "dilek");
        dll.add(2, "engin");
        System.out.println(dll);
    
        dll.remove("ali");
        System.out.println(dll);

        dll.remove(1);
        System.out.println(dll);

        
        dll.remove(0);
        System.out.println(dll);

        dll.remove(1);
        System.out.println(dll);
    }
}
