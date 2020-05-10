import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Save {

    public class Bag {
        private int capacity;
        private Set<Item> bag = new LinkedHashSet<>();
        private Item[] items;
        Set<Item> testBag = new LinkedHashSet<>();
        private int weight;
        private int price;
        private int maxPrice;

        public Bag(int capacity, Item... items) {
            this.capacity = capacity;
            this.items = items;
        }

        public Set<Item> getBestSet() {
            if (items.length <= 0) {
                return Collections.emptySet();
            }
            bag.clear();
            findBestSet(items.length);
            return bag;
        }

        private void findBestSet(int length) {
            if (length == 1) {
                return;
            }
            for (int i = 0; i < length; i++) {
                findBestSet(length - 1);
                rotate(length);
            }
        }

        private void rotate(int length) {
            int pos = items.length - length;
            Item temp = items[pos];
            for (int i = pos + 1; i < items.length; i++) {
                weight += items[i].getWeight();
                price += items[i].getPrice();
                testBag.add(items[i]);
                items[i - 1] = items[i];
            }
            items[items.length - 1] = temp;
            if (weight <= capacity) {
                if (maxPrice < price) {
                    bag.clear();
                    bag = testBag;
                    maxPrice = price;
                } else {
                    testBag.clear();
                    System.out.println("Цена комплекта в сумке = " + maxPrice + " > " + " цены предложенного комплекта" + price);
                }
            } else {
                testBag.clear();
                System.out.println("Слишком большой вес");
            }
            weight = 0;
            price = 0;


        }
    }}