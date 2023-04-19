import java.util.Stack;

public class InfixToPostfix {
    
    Stack<Character> stack = null;
    String postfix = null;

    public String convert(String infix) {
        if (infix == null) return null;
        
        String str = infix.trim();
        String[] tokens = str.split(" ");

        stack = new Stack<>();
        postfix = new String("");

        for (String token : tokens) {
            if (isNumber(token)) postfix += token + " ";
            else if (isOperator(token)) handleOperator(token);
            else {
                throw new RuntimeException("Invalid infix. Only numbers, operators and paranthesis allowed...");
            }
        }
        
        while (!stack.isEmpty()) {
            if (stack.peek() == '(') throw new RuntimeException("Invalid infix. Inbalanced paranthesis.");
            postfix += stack.pop() + " ";
        }
        return postfix;
    }
    
    private boolean isNumber(String str) {
        try {
            Integer.valueOf(str);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    private boolean isOperator(String str) {
        if (str.equals("+") || str.equals("-")) return true;
        if (str.equals("*") || str.equals("/")) return true;
        if (str.equals("(") || str.equals(")")) return true;
        return false;
    }
    
    private void handleOperator(String str) {
        Character op = str.charAt(0);
        if (op == '(') {
            stack.push(op);
        }
        else if (op == ')') {
            boolean isFound = false;
            while (!stack.isEmpty() && !isFound) {
                Character ch = stack.pop();
                if (ch == '(') isFound = true;
                else postfix += ch + " ";
            }
            if (!isFound) throw new RuntimeException("Invalid infix. Inbalanced paranthesis.");
        }
        else {
            if (stack.isEmpty()) stack.push(op);
            else {
                Character lastOp = stack.peek();
                if (numberizeOperators(lastOp) > numberizeOperators(op)) {
                    postfix += stack.pop() + " ";
                    stack.push(op);
                }
                else {
                    stack.push(op);
                }
            }
        }

    }

    private int numberizeOperators(Character ch) {
        if (ch == '*' || ch == '/') return 2;
        if (ch == '+' || ch == '-') return 1;
        return -1;
    }

    public static void main(String[] args) {
        InfixToPostfix infixConverter = new InfixToPostfix();
        System.out.println(infixConverter.convert("4 + 5"));
        System.out.println(infixConverter.convert("4 + 5 * 9"));
        System.out.println(infixConverter.convert("4 + ( 5 - 2 )"));

    }
}
