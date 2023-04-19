import java.util.Stack;

public class BalancedParantheses {
    
    private String array;
    private boolean isBalanced;

    public BalancedParantheses(String array) {
        this.array = array;
        this.isBalanced = true;
        solve();
    }

    public void changeArray(String newArray) {
        this.array = newArray;
        solve();
    }

    // O(N)
    private void solve() {
        Stack<Character> stack = new Stack<>();
        
        int idx = 0;
        while (idx < array.length() && isBalanced) {
            char ch = array.charAt(idx);
            char lastOpenOne = '0';
            switch(ch) {

                case '(':
                case '[':
                    stack.push(ch);
                    break;
                
                case ')':
                    lastOpenOne = stack.pop();
                    if (lastOpenOne != '(') {
                        isBalanced = false;
                    } 
                    break;
                
                case ']':
                    lastOpenOne = stack.pop();
                    if (lastOpenOne != '[') {
                        isBalanced = false;
                    } 
                    break;
            }

            ++idx;
        }
     
        // Eger sonda eslenmemis acik parantez stack'te kalirsa.
        if (!stack.isEmpty()) {
            isBalanced = false;
        }
    } 

    public boolean isBalanced() {
        return isBalanced;
    }


    public static void main(String[] args) {
        String array = new String("(3+5*[2/7])");
        
        BalancedParantheses balancedParantheses = new BalancedParantheses(array);    
        System.out.println(balancedParantheses.isBalanced());
    
        balancedParantheses.changeArray(new String("(((])))"));
        System.out.println(balancedParantheses.isBalanced());
    
    }
}
