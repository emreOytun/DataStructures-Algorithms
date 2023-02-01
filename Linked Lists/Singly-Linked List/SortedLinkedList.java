public class SortedLinkedList<T extends Comparable<T>> extends LinkedList<T> {

    @Override
    public void insertBeginning(T data) {
        Node<T> newNode = new Node<>(data);
        
        // If list is empty, insert at the beginning.
        if (root == null) root = newNode;

        // Insert at the beginning
        else if (data.compareTo(root.getData()) < 0) {
            newNode.setNextNode(root);
            root = newNode;
        }

        else {
            Node<T> prevNode = root;
            Node<T> curNode = root.getNextNode();
            boolean isInserted = false;
            
            // Insert in between
            while (curNode != null & !isInserted) {
                if (data.compareTo(curNode.getData()) < 0) {
                    newNode.setNextNode(curNode);
                    prevNode.setNextNode(newNode);
                    isInserted = true;
                } 
            }
        
            // Insert at the end (If it is not inserted at the beginning and the middle)
            if (!isInserted) {
                prevNode.setNextNode(curNode);
            }
        }
    }

    @Override
    public void insertEnd(T data) {
        insertBeginning(data);
    }
    
}