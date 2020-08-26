package amazon.oa;

import java.util.*;

public class websiteItem {
    public static void main(String[] args) {
        Map<String, int[]> items = new HashMap<>();
        items.put("item1", new int[]{10, 15});
        items.put("item2", new int[]{3, 4});
        items.put("item3", new int[]{17, 8});
        items.put("item4", new int[]{15, 8});
        List<String> res = itemsPerPage(4, items, 2, 1, 2, 1);
        for (String s : res) {
            System.out.println(s);
        }

    }
    static class Item {
        String name;
        int relevance;
        int price;
        public Item(String name, int relevance, int price) {
            this.name = name;
            this.relevance = relevance;
            this.price = price;
        }
    }
    static class NameIncComparator implements Comparator<Item> {
        public int compare (Item item1, Item item2) {
            return item1.name.compareTo(item2.name);
        }
    }
    static class NameDecComparator implements Comparator<Item> {
        public int compare (Item item1, Item item2) {
            return item2.name.compareTo(item1.name);
        }
    }
    static class RelevanceIncComparator implements  Comparator<Item> {
        public int compare (Item item1, Item item2) {
            return item1.relevance - item2.relevance;
        }
    }
    static class RelevanceDecComparator implements  Comparator<Item> {
        public int compare (Item item1, Item item2) {
            return item2.relevance - item1.relevance;
        }
    }
    static class PriceIncComparator implements Comparator<Item> {
        public int compare (Item item1, Item item2) {
            return item1.price - item2.price;
        }
    }
    static class PriceDecComparator implements Comparator<Item> {
        public int compare (Item item1, Item item2) {
            return item2.price - item1.price;
        }
    }

    public static List<String> itemsPerPage(int numOfItems, Map<String, int[]> items,
                                     int sortParameter, int sortOrder,
                                     int itemsPerpage, int pageNumber) {
        Comparator kmp = null;
        if (sortParameter == 0) {
            if (sortOrder == 0) {
                kmp = new NameIncComparator();
            } else {
                kmp = new NameDecComparator();
            }
        } else if (sortParameter == 1) {
            if (sortOrder == 0) {
                kmp = new RelevanceIncComparator();
            } else {
                kmp = new RelevanceDecComparator();
            }
        } else {
            if (sortOrder == 0) {
                kmp = new PriceIncComparator();
            } else {
                kmp = new PriceDecComparator();
            }
        }
        List<Item> itemList = new ArrayList<>();
        for (String key : items.keySet()) {
            itemList.add(new Item(key, items.get(key)[0], items.get(key)[1]));
        }
        Collections.sort(itemList, kmp);
        List<String> res = new ArrayList<>();
        int start = itemsPerpage * pageNumber;
        for (int i = start, j = 0; i < numOfItems && j < itemsPerpage; i++,j++) {
            res.add(itemList.get(i).name);
        }
        return res;
    }
}
