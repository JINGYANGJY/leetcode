package amazon.oa;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class suppliers {
    public static void main(String[] args) {
        int numSuppliers = 2;
        int[] inventory = {3, 5};
        int order = 6;
        System.out.println(profit(numSuppliers, inventory, order));

    }

    public static long profit(int numSuppliers, int[] inventory, int order) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i : inventory) {
            countMap.put(i, countMap.getOrDefault(i, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        for (Integer k : countMap.keySet()) {
            pq.offer(k);
        }
        long res = 0;
        while (order > 0) {
            Integer top = pq.poll();
            Integer intialCount = countMap.get(top);
            Integer count = intialCount;
            if (intialCount == null) {
                break;
            }
            while (order > 0 && count > 0) {
                res += top;
                count--;
                order--;
            }
            if (count == 0) {
                countMap.remove(top);
                countMap.put(top - 1, countMap.getOrDefault(top - 1, 0) + intialCount);
                if (!pq.isEmpty() && pq.peek() == top - 1) {
                    continue;
                }
                pq.offer(top - 1);
            } else {
                countMap.put(top, count);
                pq.offer(top);
            }
        }
        return res;
    }
}
