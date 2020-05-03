public class MainTest {
    public static void main(String[] args) {
        Queue<Integer> queue = new QueueImpl<>(5);
//        Queue<Integer> queue = new PriorityQueue<>(5);
        System.out.println(queue.insert(3));
        System.out.println(queue.insert(5));
        System.out.println(queue.insert(1));
        System.out.println(queue.insert(2));
        System.out.println(queue.insert(6));
        System.out.println(queue.remove());
        System.out.println(queue.insert(4));

        System.out.println("Queue peek: " + queue.peekFront());
        System.out.println("Queue size: " + queue.size());
        System.out.println("Queue is full: " + queue.isFull());

        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
    }
}