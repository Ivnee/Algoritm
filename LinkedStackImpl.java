public class LinkedStackImpl<E> implements Stack<E> {
    private LinkedList<E> data = new LinkedListImpl<>();


    @Override
    public void push(E value) {
        data.insertFirst(value);
    }

    @Override
    public E pop() {
        return data.removeFirst();}

    @Override
    public E peek() {
        return data.getFirst();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isFull() {
        return false;
    }
}
