public class QueueInterface {

    public static void main(String[] args) {
        IQueue<Integer> queue = new ArrayQueue<>();

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        
        while (queue.peek() != null) {
            System.out.println(queue.poll());
        }
    }

}