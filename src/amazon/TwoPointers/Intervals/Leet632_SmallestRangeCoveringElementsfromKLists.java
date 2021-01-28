package amazon.TwoPointers.Intervals;

import java.util.List;
import java.util.TreeSet;

public class Leet632_SmallestRangeCoveringElementsfromKLists {
    class Pointer {
        int listIndex;
        int pointer;
        int val;
        public Pointer(int listIndex, int pointer, int val) {
            this.listIndex = listIndex;
            this.pointer = pointer;
            this.val = val;
        }
    }
    public int[] smallestRange(List<List<Integer>> nums) {
        /*
        -> Naively, we need to find
            all range which any element in k lists as left bound, find the corresponding right bound wich can
            conver elements from k lists
            -> since the lists are sorted
            -> pointers to reduce redundant search space
            [[4,10,15,24,26]
            m
            [0,9,12,20]
            n
            [5,18,22,30]]
            k
            for each time
                we maintain k elements
                    current range
                        [min, max]
            next poll min, and add the number after min
                another range
            -> why we won't miss any solution?
                since list are sorted
                    we don't need to expand search range, because any range won't be smaller than current one
            -> min & max
                TreeSet
            Time: O(logK * (n * m))
        */
        TreeSet<Pointer> treeSet = new TreeSet<>((a, b) -> {
            int res = a.val - b.val;
            if (res == 0) {
                return a.listIndex - b.listIndex;
            }
            return res;
        });
        for (int i = 0; i < nums.size(); i++) {
            treeSet.add(new Pointer(i, 0, nums.get(i).get(0)));
        }
        int[] res = new int[2];
        res[0] = 0;
        res[1] = Integer.MAX_VALUE;
        while (treeSet.size() == nums.size()) {
            Pointer min = treeSet.first();
            Pointer max = treeSet.last();
            if (max.val - min.val < res[1] - res[0]) {
                res[0] = min.val;
                res[1] = max.val;
            }
            treeSet.remove(min);
            if (min.pointer + 1 < nums.get(min.listIndex).size()) {
                treeSet.add(new Pointer(min.listIndex, min.pointer + 1, nums.get(min.listIndex).get(min.pointer + 1)));
            }
        }
        return res;
    }
}
