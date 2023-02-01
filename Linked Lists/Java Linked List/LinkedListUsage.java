import java.util.LinkedList;

public class LinkedListUsage {

    public static void main(String[] args) {
        LinkedList<String> dll = new LinkedList<>();
        dll.addFirst("Emre"); // Adds the element to the beginning O(1)
        dll.addFirst("Can");        
        dll.addFirst("Necdet");
        dll.addLast("Ali"); // Adds the element to the last/end O(1)

        for (String name : dll) {
            System.out.println(name);
        }
        
        System.out.println(dll.peekFirst()); // Returns the first element O(1)
        System.out.println(dll.peekLast()); // Returns the last element O(1)
        
        dll.removeFirst(); // Removes the first element O(1)
        dll.removeLast(); // Removes the last element O(1)
        dll.remove("Can"); // Removes the element which positions is unknown. O(N)
    }

}