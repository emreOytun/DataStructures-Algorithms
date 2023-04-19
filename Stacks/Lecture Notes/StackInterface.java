import java.util.Stack;

public class StackInterface {

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();

        // E push(E obj) -> Push'lar ve elemani geri dondurur.
        stack.push(3);
        stack.push(5);

        // E peek()
        Integer Lastelement1 = stack.peek();
        System.out.println(Lastelement1);

        // E pop()
        Integer lastElement2 = stack.pop();
        System.out.println(lastElement2);

        // int isEmpty()
        System.out.println( stack.isEmpty() );
    }

}
