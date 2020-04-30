public interface Array<E> {
    int INITIAL_CAPASITY=16;
    void add(E value);
    int indexOf(E value);
    E remove(int index);
    boolean remove(E value);
    void sortBubble();
    void sortSelect();
    E[] returnArray();
    void sortInsert();
    int size();
    E get(int index);
    default void printArray(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i <size() ; i++) {
            sb.append(get(i)).append(", ");
        }
        sb.setLength(sb.length()-2);
        sb.append("]");
        System.out.println(sb);

    }


}
