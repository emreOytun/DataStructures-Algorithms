/*
        Question: The method Node<E> returnUniqe() of class LinkedListRec<E> returns a new list
    that contains only the uniqe elements of list.For example 1→4 →5 →3 →2 →1 →5 →7 then
    returned list would be 4 → 3 → 2 →7.Implement method returnUniqe() by writing
    additional recursive methods only. Use method equals for comparasion, do not modify the
    list.(Hint:write a recursive method that searches a given value in a list, and write another
    recursive method.)
*/

import java.util.HashSet;
import java.util.Iterator;

public class FindUniqueItems <E> {

    // O(N)
    public Node<E> returnUnique(Node<E> linkedlist) {
        HashSet<E> unique = new HashSet<>();
        HashSet<E> nonUnique = new HashSet<>();
        findUniqueSet(unique,  nonUnique, linkedlist);
        
        // If there is no unique elements return null.
        if (unique.isEmpty()) return null;

        // If there are elements, then first initialize the root node and add the others.
        Iterator<E> it = unique.iterator();
        Node<E> root = new Node<>(it.next());
        while (it.hasNext()) {
            root.setNextNode(new Node<>(it.next()));
            root = root.getNextNode();
        } 
        return root;
    }   

    // Finds the unique set of elements by traversing the list only once.
    // Adds the element to the unique set if it is encountered first.
    // Removes the element from the unique set if it is encountered again and adds it to the nonUnique set to check it later again.
    // O(N)
    private void findUniqueSet(HashSet<E> unique, HashSet<E> nonUnique, Node<E> node) {
        if (node == null) return;
        if (unique.contains(node.getData())) {
            unique.remove(node.getData());
            nonUnique.add(node.getData());
        }
        else if (!nonUnique.contains(node.getData())) {
            unique.add(node.getData());
        }
        findUniqueSet(unique, nonUnique, node.getNextNode());
    }
}
