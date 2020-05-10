
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Treners  {
    public static void main(String[] args) {
//Задание 2
        Item [] items = {new Item(1,1),new Item(2,2),new Item(3,3), new Item(4,2)};
        Bag bag = new Bag(5,items);
        bag.getBestSet().forEach(System.out::println);
//Задание 1
        Exp e = new Exp();
        System.out.println(e.Exp(2,-2));
    }
    private char []word;
    private Set<String> result = new LinkedHashSet<>();


    public Treners(String  word) {
        this.word = word.toCharArray();
    }
    public Set<String >getAnagramm() {
        if(word.length <=0){
            return Collections.emptySet();
        }
        result.clear();
        consumeAnagramm(word.length);
        return result;
    }
    private void consumeAnagramm(int length) {
        if(length == 1){
            return;
        }
        for (int i = 0; i <length ; i++) {
            consumeAnagramm(length - 1);
            result.add(String.valueOf(word));
            rotate(length);

        }
    }
private void rotate(int length){
        int pos = word.length - length;
        char temp = word[pos];
    for (int i = pos+ 1; i < word.length ; i++) {
        word[i-1]=word[i];
    }
    word[word.length-1 ] = temp;
}
}