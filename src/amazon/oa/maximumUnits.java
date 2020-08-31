package amazon.oa;

import java.util.*;

/**
 * Amazon needs to create a shipment to fill a truck. all of the products in the warehouse
 * are in boxes of the same size. Each product is packed in same nubmer of unit per box.
 * given number of boxes the truck can hold. determine the maximum number of
 * units of any mix of products that can be shipped
 */

public class maximumUnits {
    public static void main(String[] args) {
        System.out.println(getMaxUnit(3, Arrays.asList(1, 2, 3), 3, Arrays.asList(3, 2, 1), 3));
    }
    private static long getMaxUnit(int num, List<Integer> boxes, int unitSize, List<Integer> unitsPerBox, long truckSize) {
        long res = 0;
        Map<Integer, Integer> unitsMap = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        for (int i = 0; i < unitSize; i++) {
            unitsMap.put(unitsPerBox.get(i), boxes.get(i));
            pq.offer(unitsPerBox.get(i));
        }
        while (truckSize > 0) {
            Integer top = pq.poll();
            Integer count = unitsMap.get(top);
            while (truckSize > 0 && count > 0) {
                res += top;
                count--;
                truckSize--;
            }
            if (count == 0) {
                unitsMap.remove(top);
            } else {
                unitsMap.put(top, count);
            }
        }
        return res;
    }

}
