import java.util.Iterator;
import java.util.Queue;

public interface IQueue<E> {
    boolean offer(E newItem); // elemani queue'ya ekler ve her zaman true dondurur.
    E poll(); // eleman yoksa null dondurur. bastaki elemani siler.
    E peek(); // eleman yoksa null dondurur. bastaki elemani silmez.
    E remove(); // poll'la aynidir, sadece NoSuchElementException atar. 
    E element(); // peek'le aynidir, sadece NoSuchElementException atar.
    
    boolean isEmpty();
    int size();
    Iterator<E> iterator();
}
