public class Main {
    public static void main(String[] args) {
        StackMaxElement<Integer> s = new StackMaxElement<>();
        
        s.push(3);
        System.out.println(s.maxElement());

        s.push(5);
        System.out.println(s.maxElement());

        s.push(2);
        System.out.println(s.maxElement());

        s.push(20);
        System.out.println(s.maxElement());

        s.pop();
        System.out.println(s.maxElement());

        s.pop();
        System.out.println(s.maxElement());

        s.pop();
        System.out.println(s.maxElement());
    }
}
