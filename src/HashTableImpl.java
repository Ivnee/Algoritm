import java.util.LinkedList;
import java.util.List;

public class HashTableImpl<K, V> implements HashTable<K, V> {

    private final LinkedList<Item<K,V>>[] data;
    private int size;
    static class Item<K, V> implements Entry<K, V> {
        private final K key;
        private V value;

        public Item(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }
    }


    @SuppressWarnings("unchecked")
    public HashTableImpl(int maxSize) {
        this.data = new LinkedList[maxSize];
        for (int i=0; i < data.length ; i++){
            data[i] = new LinkedList<>();
        }

    }

    private int hashFunc(K key) {
        return Math.abs(key.hashCode() % data.length);
    }

    @Override
    public boolean put(K key, V value) {
        int index = hashFunc(key);
        for (int i = 0; i < data[index].size() ; i++) {
            if(data[index].get(i).key.equals(key)){
                data[index].get(i).value = value;
                return true;
            }
            if(data[index].get(i).key == null){
                data[index].add(new Item<K,V>(key,value));
                size++;
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int dataIndex = hashFunc(key);
        int index = indexOf(key);
        return index != -1 ? data[dataIndex].get(index).value:null;
    }

    private int indexOf(K key) {
        int index = hashFunc(key);
        for (int i = 0; i <data[index].size() ; i++) {
            if(data[index].get(i).key.equals(key)){
            return i;
            }
        }
        return -1;
    }

    @Override
    public V remove(K key) {
        int dataIndex = hashFunc(key);
        int index = indexOf(key);
        if (index == -1) {
            return null;
        }
        Item<K, V> item = data[index].get(index);
        data[dataIndex].remove(index);
        size--;
        return item.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void display() {
        System.out.println("---------");
        for (List<Item<K, V>> datum : data) {
            System.out.println(datum);
        }
        System.out.println("---------");
    }

    protected int getStep(K key) {
        return 1;
    }
}
