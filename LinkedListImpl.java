import java.util.Iterator;

public class LinkedListImpl<E> implements LinkedList <E> {
    protected Entry<E> firstElement;
    protected int size;
    @Override
    public void insertFirst(E value) {
        Entry<E> enrty = new Entry<>(value);
        enrty.next = firstElement;
        firstElement = enrty;
        size++;

    }

    @Override
    public E removeFirst() {
        if(isEmpty()){
            return null;
        }
        E removedVelue = firstElement.value;
        firstElement = firstElement.next;
        size--;
        return removedVelue;
    }
    public boolean remove(E value){
        Entry<E> current = firstElement;
        Entry<E> previous = null;
        while(current != null){
            if(current.value.equals(value)){
                break;
            }
            previous=current;
            current = current.next;
        }
        if(current == null){
            return false;
        }
        if(current == firstElement){
            firstElement = firstElement.next;
        }else {
            previous.next = current.next;
        }
        size--;
        return true;
    }
    @Override
    public boolean contains(E value) {
        Entry<E>  current =firstElement;
        while ((current!= null)){
            if(current.value.equals(value)){
                return true;
            }
            current=current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void display() {
        System.out.println("-------");
        Entry<E> current = firstElement;
        while (current != null){
            System.out.println(current.value);
            current = current.next;
        }
        System.out.println("-------");

    }

    @Override
    public E getFirst() {
        return firstElement != null ? firstElement.value: null;
    }

    }

    class Entry<E>{
        E value;
        Entry<E> next;

        public Entry(E value) {
            this.value = value;
        }
    }
}
