
public class MainTest {
    public static void main(String[] args) {
        Dequeue<Integer> deque = new DequeImpl<>(4);
        System.out.println(deque.insertLeft(3));
        System.out.println(deque.insertLeft(2));
        System.out.println(deque.insertLeft(1));
        System.out.println(deque.insertRight(4));
        System.out.println(deque.insertRight(5));

        System.out.println("Deque peekFront: " + deque.peekFront());
        System.out.println("Deque peekBack: " + deque.peekBack());
        System.out.println("Deque size: " + deque.size());
        System.out.println("Deque is full: " + deque.isFull());

        while (!deque.isEmpty()){
            System.out.println(deque.removeLeft());
            System.out.println(deque.removeRight());
        }

        reverseMSG("abcd 123123 zel");
    }


    public static void reverseMSG(String msg){
        String[] arr = msg.split("");
        //String[] arr = msg.split(" ");
        StackImpl<String> st = new StackImpl<>(arr.length);

        int i=0;
        while (!st.isFull()){
            st.push(arr[i++]);
        }
        while (!st.isEmpty()){
            System.out.print(st.pop());
            //System.out.print(st.pop() + " ");
        }
    }
}