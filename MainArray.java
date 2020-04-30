import java.sql.Time;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainArray {
    public static void main(String[] args) {
        Random rd = new Random();
        Integer [] arr1 = new Integer[50000];
        Integer [] arr2= new Integer[50000];
        Integer [] arr3= new Integer[50000];
        for (int i = 0; i <50000 ; i++) {
            arr1[i]=rd.nextInt(100);
        }
        System.arraycopy(arr1,0,arr2,0, arr1.length);
        System.arraycopy(arr1,0,arr3,0, arr1.length);
        Array<Integer>data1 = new ArrayImpl<>(arr1);
        Array<Integer>data2 = new ArrayImpl<>(arr2);
        Array<Integer>data3 = new ArrayImpl<>(arr3);


        new Thread(()->{
            long start1 =System.currentTimeMillis();
            data1.printArray();
            data1.sortBubble();
            data1.printArray();
            long end1 = System.currentTimeMillis();
            System.out.println(end1-start1 + " пузырьковая сортировка /end");
        }).start();

        new Thread(()->{
            long start2 = System.currentTimeMillis();
            data2.printArray();
            data2.sortSelect();
            data2.printArray();
            long end2 = System.currentTimeMillis();
            System.out.println(end2-start2 + " сортировка выборкой /end");

        }).start();
        new Thread(()->{
            long start3 = System.currentTimeMillis();
            data3.printArray();
            data3.sortInsert();
            data3.printArray();
            long end3 = System.currentTimeMillis();
            System.out.println(end3-start3+" Сортировка вставкой /end");}).start();
    }
}
