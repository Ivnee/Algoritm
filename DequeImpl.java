import jdk.nashorn.internal.ir.annotations.Ignore;

public class DequeImpl<E> implements Dequeue<E>{

    private static final int DEFAULT_RIGHT = 0;
    private static final int DEFAULT_LEFT = 0;

    protected final E[] data;
    protected int size;

    private int right;
    private int left;
    public DequeImpl(int maxSize) {
        this.data = (E[]) new Object [maxSize];
        this.left = DEFAULT_LEFT;
        this.right = DEFAULT_RIGHT;
    }

    @Override
    public boolean insertRight(E value) {
        if (isFull()) {
            return false;
        }
        if(right == 0 && data[right] != null){
            right++;
        }
        if (right == lastIndex()) {
            right = DEFAULT_RIGHT;
        }

        data[right++] = value;
        size++;
        return true;

    }

    @Override
    public boolean insertLeft(E value) {
        if (isFull()) {
            return false;
        }
        if(left == 0 && data[left] != null){
            left = lastIndex();
        }
        if (left == 0) {
            data[left] = value;
            left = lastIndex();
            size++;
            return true;
        }
        data[left--] = value;
        size++;
        return true;
    }

    @Override
    public E removeLeft() {
        if (isEmpty()) {
            return null;
        }
        if (left == lastIndex()) {
            left = DEFAULT_LEFT;
        }
        E removedValue = data[++left];
        size--;
        return removedValue;
    }

    @Override
    public E removeRight() {
        if (isEmpty()) {
            return null;
        }

        if (right == 0) {
            right = lastIndex();
            E removedValue = data[0];
            size--;
            return removedValue;
        }
        E removedValue = data[--right];
        size--;
        return removedValue;
    }

    @Override
    public E peekFront() {
        return data[right-1];
    }

    @Override
    public E peekBack() {
        return data[left+1];
    }
    public int lastIndex(){
        return data.length-1;
    }

    @Override
    @Deprecated
    public boolean insert(E value) {
        return false;
    }

    @Override
    @Deprecated
    public E remove() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    public boolean isFull(){
        return data.length == size;
    }
}
