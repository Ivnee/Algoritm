public interface Dequeue<E> {
    boolean insertLeft(E value);
    boolean insertRight(E value);
    E removeRight();
    E removeLeft();
    E pickFront();
    E pickBack();

}
