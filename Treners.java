
public class Treners  {
    public static void main(String[] args) {
        LinkedList <Integer> l = new LinkedListImpl<>();
        l.insertFirst(1);
        l.insertFirst(2);
        l.insertFirst(3);
        l.insertFirst(4);
        for (Integer integer : l) {
            System.out.println(integer);
        }
    }
}