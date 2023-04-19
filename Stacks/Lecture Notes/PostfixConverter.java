import java.util.Stack;

public class PostfixConverter {
    
    private Stack<String> expressions;

    public String convert(String postfix) {
        if (postfix == null) {
            return null;
        }

        // Initialize expressions stack.
        expressions = new Stack<>();

        postfix = postfix.trim();
        String[] tokens = postfix.split(" ");
        
        for (String token : tokens) {

            if (isNumber(token)) {
                expressions.push(token);
            }

            else if (isOperator(token)) {
                processOperator(token.charAt(0));
            }

            else {
                throw new RuntimeException("INVALID POSTFIX EXPRESSION");
            }
        }
        
        if (expressions.isEmpty()) {
            throw new RuntimeException("INVALID POSTFIX EXPRESSION");
        }
        return expressions.pop();
    }  

    private void processOperator(char op) {
        String rhs = expressions.pop();
        String lhs = expressions.pop();
        if (!isNumber(lhs)) {   // Icerde sadece sayi varsa parantez eklemeye gerek yok, bu kontrol onun icin.
            lhs = "(" + lhs  + ")";
        }

        String result =  "( " + lhs + " " + op + " " + rhs +" )";
        expressions.push(result);
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
        PostfixConverter postfixConverter = new PostfixConverter();

        String infix = "4 * 5 + 2";
        String postfix = converter.convert(infix);
        System.out.println(postfix);

        int result = calculator.calculate(postfix);
        String postfixResult = postfixConverter.convert(postfix);
        System.out.println(postfixResult);
        System.out.println(result);

        String infix2 = "4 + ( 5 * 2 ) - 7 * 12 / 2";
        String postfix2 = converter.convert(infix2);
        System.out.println(postfix2);

        int result2 = calculator.calculate(postfix2);
        String postfixResult2 = postfixConverter.convert(postfix2);
        System.out.println(postfixResult2);
        System.out.println(result2);
    }
}
