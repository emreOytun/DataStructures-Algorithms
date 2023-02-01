import java.util.Stack;

public class InfixToPostfix {

    // Operand priority checks
    public static boolean priorityCheck(char operand1, char operand2) {
        if (operand2 == '^') return false;
        if (operand1 == '^' || operand1 == '*' || operand1 == '/') return true;
        if (operand2 == '+' || operand2 == '-') return true;
        return false;
    }

    // ------------ Infix to Postfix Conversion --------------------
    // Reads the given expression char-by-char and does the followings:
    // 1) If it's space, make inNumber = false.
    // 2) If it's open parantheses then put it to the stack so that we know where the closed parantheses is opened.
    // 3) If it's closed parantheses then pop the stack until open parantheses.
    // 4) If it's operand: 
    //      i) If stack is empty or the last element is parantheses in the stack, then put the operand to the stack.
    //      ii) If the last element in the stack is operand :
    //          ii.1) If the new one has priority, then put it to the stack since this one should done first.
    //          ii.2) Otherwise, pop the last one and put it to the postfix; then put the new one to the stack.
    // 5) Empty the stack at the last.
    public static String convertExpression(String expression) {

        Stack<Character> operandsStack = new Stack<>();
        String postfix = "";

        boolean inNumber = true;
        for (int i = 0; i < expression.length(); ++i) {
            
            char ch = expression.charAt(i);
            switch (ch) {

                case ' ' : 
                    inNumber = false;
                    break;

                case '(' :
                    operandsStack.push('(');
                    inNumber = false;
                    break;

                case ')' :
                    while (operandsStack.peek() != '(') {
                        postfix += " " + operandsStack.pop();
                    }
                    operandsStack.pop();
                    inNumber = false;
                    break;
                
                case '+' :
                case '-' :
                case '*' :
                case '/' :
                case '^' :
                    if (operandsStack.isEmpty() || operandsStack.peek() == ')' || operandsStack.peek() == '(') {
                        operandsStack.push(ch);
                    }

                    else if (priorityCheck(ch, operandsStack.peek())) {
                        operandsStack.push(ch);
                    }

                    else {
                        postfix += " " + operandsStack.pop();
                        operandsStack.push(ch);
                    }

                    inNumber = false;
                    break;
                
                default :
                    if (inNumber) postfix += ch;
                    else postfix += " " + ch;
                    inNumber = true;    
            }

        }

        while (!operandsStack.isEmpty()) {
            postfix += " " + operandsStack.pop();
        }

        return postfix;
    }


    // ------------ Postfix to Infix Conversion: ----------------
    // Reads the given expression char-by-char and does the followings:
    // 1) If it's space, then check if a number is read before; if it's push the number.
    // 2) If it's operand, then pop last 2 values and make the calculation, then push the result to the value stack. Check if a number is read before.
    // 3) If it's a digit, then put it to the number string.
    // 4) Finally, pop the last value in the stack.
    public static Double calculatePostfix(String postfix) {
        Stack<Double> resultStack = new Stack<>();

        boolean isNumber = false;
        String number = "";
        for (int i = 0; i < postfix.length(); ++i) {
            
            char ch = postfix.charAt(i);
            switch (ch) {

                case ' ':

                    if (isNumber) {
                        resultStack.push(Double.parseDouble(number));
                        number = "";
                    }
                    isNumber = false;
                    break;

                case '+':
                case '-':
                case '*':
                case '/':
                    Double value2 = resultStack.pop();
                    Double value1 = resultStack.pop();
                    
                    if (ch == '+') resultStack.push(value1 + value2);
                    else if (ch == '-') resultStack.push(value1 - value2);
                    else if (ch == '*') resultStack.push(value1 * value2);
                    else if (ch == '/') resultStack.push(value1 / value2);

                    if (isNumber) {
                        resultStack.push(Double.parseDouble(number));
                        number = "";
                    }
                    isNumber = false;
                    break;

                default :
                    number += ch;
                    isNumber = true;
            }

        }

        return resultStack.pop();
    }

    public static void main(String[] args) {
        String postfix = convertExpression("3 + 4 * 2");
        System.out.println(postfix);
        System.out.println("Postfix result: " + calculatePostfix(postfix));
    }
}