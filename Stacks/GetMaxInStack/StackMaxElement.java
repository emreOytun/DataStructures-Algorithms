import java.util.Stack;

public class StackMaxElement<T extends Comparable<T>> {

    private Stack<T> stack = new Stack<>();
    private Stack<T> maxElements = new Stack<>();

    // O(1)
    // When pushing an element to the stack, we compare it with the last element of the maxElements stack and push it to the 
    // maxElements stack if it's bigger than the last one. 
    // In that way; when max element is popped from the stack we directly have the max one after this element that popped out.
    // If we keep only 1 max element, we cannot reach the 2nd max element if this element popped out. 
    // This is the reason to keep an extra stack for max elements.
    public void push(T data) {
        if (maxElements.size() == 0) maxElements.push(data);
        else if (data.compareTo(maxElements.peek()) > 0) maxElements.push(data);  
        
        stack.push(data);
    }

    // O(1)
    public T pop() {
        if (stack.peek().compareTo(maxElements.peek()) == 0) maxElements.pop();
        if (stack.size() == 0) return null; // IMPORTANT**: Don't forget to check the size in pop and peek functions.
        return stack.pop();
    }

    // O(1)
    public T peek() {
        if (stack.size() == 0) return null;
        return stack.peek();
    }

    // O(1)
    public T maxElement() {
        return maxElements.peek();
    }

    // O(1)
    public int size() {
        return stack.size();
    }

    // O(1)
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}