public class Main {
    public static void main(String[] args) {
        QueueWithStack<Integer> q = new QueueWithStack<>();
        q.push(3);
        q.push(4);

        System.out.println(q.pop());

        q.push(2);
        System.out.println(q.pop());
        System.out.println(q.pop());
        
        System.out.println(q.pop());
    }   
}
