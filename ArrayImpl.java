import java.util.Arrays;
import java.util.Random;

public class ArrayImpl<E extends Object & Comparable<? super  E>> implements Array<E> {
    private int size;
    private E [] data;
    Random rd = new Random();

    public ArrayImpl() {
        this(null,INITIAL_CAPASITY);
    }

    public ArrayImpl(E... data) {
        this(data,data.length);


    }
    public ArrayImpl(int initial_capacity) {
        this(null,initial_capacity);
    }

    private ArrayImpl(E[] data, int size) {
        this.size = data != null ? data.length : 0;
        this.data = data != null ? data : (E[]) new Object[size] ;
    }
    @Override
    public E[] returnArray(){
        return data;
    }

    @Override
    public void add(E value) {
        checkAndGrow();
        data[size++]=value;
    }

    @Override
    public int indexOf(E value) {
        for (int i = 0; i <size ; i++) {
            if(data[i].equals(value)){
                return i;
            }
        }
        return -1;
    }
    @Override
    public E remove(int index) {
        checkIndex(index);
        E removedValue = data[index];
        for (int i = index; i < size -1  ; i++) {
            data[i] = data[i+1];
        }
        data[size-1]=null;
        size--;
        return removedValue;
    }

    @Override
    public boolean remove(E value) {
        int index = indexOf(value);
        try {
            return remove(index) != null;
        }catch (IndexOutOfBoundsException e){
            return false;
        }
    }
    public void checkAndGrow(){
        if(data.length == size){
            data = Arrays.copyOf(data, data.length * 2);
        }
    }
    public void checkIndex(int index) {
        if(index <0 || index >= data.length){
            String err = String.format("Неверный индекс массива - %d, размер массива = %d", index, data.length);
            throw new IndexOutOfBoundsException(err);
        }
    }
    //Методы сортировки

    @Override
    public void sortBubble() {
        for (int i = 0; i <size -1; i++) {
            boolean wasChanged = false;
            for (int j = 0; j <size -1 ; j++) {
                if(data[j].compareTo(data[j+1]) > 0 ){
                    swap(j,j+1);
                    wasChanged = true;
                }
            }
            if(!wasChanged){
                break;
            }
        }
    }

    @Override
    public void sortSelect() {
        for (int i = 0; i <size ; i++) {
            int minIndex =i;
            for (int j = i+1; j <size ; j++) {
                if(data[j].compareTo(data[minIndex]) < 0){
                    minIndex = j;
                }
            }
            if(minIndex != i){
                swap(minIndex,i);
            }
        }

    }

    @Override
    public void sortInsert() {
        for (int i = 0; i <size ; i++) {
            E temp = data[i];
            int in = i;
            while (in > 0 && data[in-1].compareTo(temp)>0){
                data[in]=data[in-1];
                in--;
            }
            data[in] = temp;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    public void swap(int index1 , int index2) {
        E temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }
}
