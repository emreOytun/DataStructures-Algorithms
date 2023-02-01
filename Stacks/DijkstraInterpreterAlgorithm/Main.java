public class Main {
    public static void main(String[] args) {
        Algorithm interpreter = new Algorithm();
        interpreter.interpretExpression("( ( 5 + 3 ) - 2 )");
        System.out.println(interpreter.result());

        interpreter.interpretExpression("5 + 3 - 2");
        System.out.println(interpreter.result());
    }
}
