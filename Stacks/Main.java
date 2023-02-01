public class Main {

    public static <T> void printStack(StackLinkedList<T> s) {
        StackLinkedList<T> copy = new StackLinkedList<>();
        
        while (!s.isEmpty()) {
            copy.push(s.peek());
            System.out.println(copy.peek());
            s.pop();
        }
    
        while (!copy.isEmpty()) {
            s.push(copy.peek());
            copy.pop();
        }
    }

    public static void main(String[] args) {
        StackLinkedList<Integer> myStack = new StackLinkedList<>();

        myStack.push(2);
        System.out.println(myStack.peek());

        myStack.push(4);
        System.out.println(myStack.peek());
    
        myStack.pop();
        System.out.println(myStack.peek());
    
        myStack.push(5);
        myStack.push(7);
        printStack(myStack);

    }
}
