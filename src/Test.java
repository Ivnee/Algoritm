import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random rd = new Random();
        Tree<Integer> tree = new TreeImpl<>();
        for (int i = 0; i <100 ; i++) {
            tree = TreeImpl.createRandomIntTree(4);
        }

    }
}
