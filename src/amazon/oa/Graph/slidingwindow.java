package amazon.oa.Graph;

import java.util.*;

/**
 * https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/
 * Given an array of integers arr and an integer k.
 * Find the least number of unique integers after removing exactly k elements.
 */

public class slidingwindow {
    public static void main(String[] args) {
        int[] arr = {5, 5, 4};
        System.out.println(findLeastNumOfUniqueInts(arr, 1));
        //arr = [4,3,1,1,3,3,2], k = 3

    }

    public static int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        List<Integer> list = new ArrayList<>(map.values()); // ❌
        Collections.sort(list);
        int i = 0;
        for (i = 0; i < list.size(); i++) {
            if (k <= 0) {
                break;
            }
            k -= list.get(i);
        }
        return k < 0 ? list.size() - i + 1 : list.size() - i; // ❌
    }
}
