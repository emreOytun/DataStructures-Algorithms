import java.util.Stack;

public class PostfixCalculator {
    
    private Stack<Integer> numbers;
   
    public int calculate(String postfix) throws RuntimeException {
        if (postfix == null) {
            throw new RuntimeException("INVALID POSTFIX EXPRESSION");
        }

        // Initialize numbers stack.
        numbers = new Stack<>();

        postfix = postfix.trim();
        String[] tokens = postfix.split(" ");

        for (String token : tokens) {

            if (isNumber(token)) {
                numbers.push(Integer.valueOf(token));
            }

            else if (isOperator(token)) {
                processOperator(token.charAt(0));
            }

            else {
                throw new RuntimeException("INVALID POSTFIX EXPRESSION");
            }
        }
        
        return numbers.pop();
    }

    private void processOperator(char op) {
        // ONEMLI**: LHS VE RHS OLARAK BELIRTMEK ONEMLI.
        int rhs = numbers.pop();
        int lhs = numbers.pop();
        int result = 0;

        if (op == '+') result = lhs + rhs;
        else if (op == '-') result = lhs - rhs;
        else if (op == '*') result = lhs * rhs;
        else if (op == '/') result = lhs / rhs;

        numbers.push(result);
    }

    private boolean isNumber(String token) {
        try {
            Integer num = Integer.valueOf(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isOperator(String str) {
        if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") ||
        str.equals("(") || str.equals(")")) {
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        InfixConverter converter = new InfixConverter();
        PostfixCalculator calculator = new PostfixCalculator();

        String infix = "4 * 5 + 2";
        String postfix = converter.convert(infix);
        System.out.println(postfix);

        int result = calculator.calculate(postfix);
        System.out.println(result);

        String infix2 = "4 + ( 5 * 2 ) - 7 * 12 / 2";
        String postfix2 = converter.convert(infix2);
        System.out.println(postfix2);

        int result2 = calculator.calculate(postfix2);
        System.out.println(result2);
    }
}
