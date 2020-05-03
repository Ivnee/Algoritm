public interface Dequeue<E> extends Queue<E> {
    boolean insertLeft(E value);
    boolean insertRight(E value);
    E removeRight();
    E removeLeft();
    E peekBack();

}
