package QueueWithArray;

public class Test {
    public static void main(String[] args) {
        QueueWithArray<Integer> q = new QueueWithArray<Integer>();
        q.push(1);
        q.push(2);
        q.push(3);
        q.push(4);
        q.push(5);
        q.push(6);
        q.push(7);
        q.push(8);
        q.push(9);
        q.push(10);
        q.push(11);
        q.push(12);

        while (!q.isEmpty()) {
            System.out.println(q.pop());
        }
    }
}
