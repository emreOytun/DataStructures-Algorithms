import java.util.Stack;

public class Algorithm {

    private Stack<String> operationStack;
    private Stack<Double> valueStack;

    public Algorithm() {
        this.operationStack = new Stack<>();
        this.valueStack = new Stack<>();
    }

    // TODO: Read char-by-char and ignore whitespaces manually. Throws exception when there is a parsing problem/casting problem.
    public void interpretExpression(String expression) {
        String[] expressionArray = expression.split(" ");    
    
        for (String s : expressionArray) {
            System.out.println("s: " + s);
            if (s.equals("+")) {
                this.operationStack.push(s);
            }
            else if (s.equals("*")) {
                this.operationStack.push(s);
            }
            else if (s.equals("-")) {
                this.operationStack.push(s);
            }
            else if (s.equals(")")) {
                String operation = this.operationStack.pop();
                Double value2 = this.valueStack.pop();
                Double value1 = this.valueStack.pop();

                makeCalculation(operation, value1, value2);

            }
            else if (!s.equals("(")) {
                this.valueStack.push(Double.parseDouble(s));
            }

        }
    
        this.valueStack.push(calculateRestWrapper());
    }

    private void makeCalculation(String operation, Double value1, Double value2) {
        if (operation.equals("+")) {
            this.valueStack.push(value1 + value2);
        }
        else if (operation.equals("-")) {
            this.valueStack.push(value1 - value2);
        }
        else if (operation.equals("*")) {
            this.valueStack.push(value1 * value2);
        }
    }

    // This function calculates the rest operations when there are no more parantheses to handle.
    private Double calculateRestWrapper() throws RuntimeException {
        if (valueStack.size() != operationStack.size() + 1) {
            throw new RuntimeException("Parsing error");
        }
    
        return calculateRest();
    }

    private Double calculateRest() {
        // If there is no operation rest, then return the last value in the value stack.
        if (operationStack.size() == 0) return valueStack.pop();
        
        Double value2 = valueStack.pop();
        String operation = operationStack.pop();
        Double value1 = calculateRest(); // Get the value1 from the result of the rest.
        
        makeCalculation(operation, value1, value2); // Makecalculation function pushes the result to the value stack.
        return valueStack.pop();
    }

    public Double result() {
       return this.valueStack.pop();
    }
}