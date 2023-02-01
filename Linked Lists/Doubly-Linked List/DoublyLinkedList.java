public class DoublyLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;

    public void insert(T data) {
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            head = newNode;
            tail = newNode;
        }

        else {
            newNode.setPrevNode(tail);
            tail.setNextNode(newNode);
            tail = newNode;
        }
    }

    public void remove(T data) {
        // If list is empty, then return.
        if (head == null) return;

        // Remove from the beginning
        if (head.getData().equals(data)) {
            head.getNextNode().setPrevNode(null);
            head = head.getNextNode();
        }
    
        else {
            boolean isRemoved = false;
            Node<T> curNode = new Node<>(data);

            // Remove from the between.
            while (curNode != null && !isRemoved) {
                if (curNode.getData().equals(data)) {
                    curNode.getPrevNode().setNextNode(curNode.getNextNode());
                    curNode.getNextNode().setPrevNode(curNode.getPrevNode());
                    curNode = null;
                    isRemoved = true;
                }
            }

        }
    }

}