import java.util.Stack;

public class InfixConverter {
    
    // Class degiskeni olarak tutmamin sebebi, fonksiyonlara surekli parametre gondermemek.
    private String postfix;
    private Stack<Character> operatorStack;

    public String convert(String infix) throws RuntimeException {
        if (infix == null) {
            return null;
        }

        // Check if the parantheses are balanced.
        BalancedParantheses balancedParantheses = new BalancedParantheses(infix);
        if (!balancedParantheses.isBalanced()) {
            throw new RuntimeException("NOT A VALID INFIX EXPRESSION - INBALANCED PARANTHESES");
        }

        // Initialize postfix and operatorStack.
        postfix = new String("");
        operatorStack = new Stack<>();

        infix = infix.trim();   // Trim the infix so that if it has spaces at front, they will not be a problem.
        String[] tokens = infix.split(" "); // Split the string using split(" ") method.
        
        for (String token : tokens) {
            
            if (isNumber(token)) {
                postfix += " " + token;
            }

            else if (isOperator(token)) {
                processOperator(token.charAt(0));
            }

            else {
                throw new RuntimeException("INVALID INFIX EXPRESSION");
            }
        }

        //  Son durumda stack'te eleman kaldiysa postfix'e ekle.
        while (!operatorStack.isEmpty()) {
            postfix += " " + operatorStack.pop();
        }

        return postfix;
    }

    // ONEMLI**: Bir string'in numeric olup olmadigi bu sekilde Integer.valueOf'un sorun cikarip cikartmadigina gore test edilir.
    private boolean isNumber(String token) {
        try {
            Integer num = Integer.valueOf(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ONEMLI**: Equals methodunu kullanmazsan hatali olur.
    private boolean isOperator(String str) {
        if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") ||
        str.equals("(") || str.equals(")")) {
                return true;
        }
        return false;
    }

    // Rastgele precedence atiyorum.
    private int precedence(char op) {
       if (op == '*' || op == '/') return 1;
       return 0;
    }  

    private void processOperator(char op) {
        // Acik parantezse stack'e koy.
        if (op == '(') {
            operatorStack.push(op);
        }

        // Kapali parantezse acik paranteze kadar bosalt.
        else if (op == ')') {
            while (operatorStack.peek() != '(') {
                char opInside = operatorStack.pop();
                postfix += " " + opInside;
            }
            operatorStack.pop();
        }

        // Stack bossa dogrudan operatoru koy.
        else if (operatorStack.empty()) {
            operatorStack.push(op);    
        }

        // Son operator ile preceden kiyasla.
        else {
            char lastOp = operatorStack.peek();
            if (precedence(op) > precedence(lastOp)) {
                operatorStack.push(op);
            }
            else {
                postfix += " " + lastOp;
                operatorStack.pop();
                operatorStack.push(op);
            }
        }
    }

    public static void main(String[] args) {
        InfixConverter converter = new InfixConverter();

        String infix = "4 * 5 + 2";
        System.out.println(converter.convert(infix));
    }
}