public class Main {
    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();
        q.enqueue(3);
        q.enqueue(5);
        System.out.println(q.size());

        System.out.println(q.dequeue());
        System.out.println(q.size());
                
        System.out.println(q.dequeue());
        System.out.println(q.size());

        System.out.println(q.dequeue());
        System.out.println(q.size());
    }
}
