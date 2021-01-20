package amazon.TwoPointers.Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Leet763_PartitionLabels {
    public List<Integer> partitionLabels(String S) {
        /*
            ababcbaca   defegdehijhklij
            a:[first occurance, last occurance]
            b
            d
            d
            e
            next step?
                merge all intervals which have overlap
                - Clarification?
                    Is s only contains lowercase?
                represent interval
                int[] start
                int[] end
                intervals
                    to merge?
                    -> [M1]
                           [M2]
                        sort by ending index
                        has overlap? merge
                        otherwise interval into res
            -> return non-overlapping components
        */
        int[] start = new int[26];
        int[] end = new int[26];
        Arrays.fill(start, -1);
        Arrays.fill(end, -1);
        // get all intervals
        char[] arr = S.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (start[arr[i] - 'a'] == -1) {
                start[arr[i] - 'a'] = i;
                end[arr[i] - 'a'] = i; // only one occurance
            } else {
                end[arr[i] - 'a'] = i;
            }
        }
        List<int[]> intervals = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (start[i] != -1) {
                intervals.add(new int[]{start[i], end[i]});
            }
        }
        // merge all intervals
        Collections.sort(intervals, (a, b) -> {
            int res = a[0] - b[0];
            if (res == 0) {
                return a[1] - b[1] < 0 ? 1 : -1;
            }
            return res;
        });
        List<int[]> mergedIntervals = new ArrayList<>();
        mergedIntervals.add(intervals.get(0));
        int[] prev = mergedIntervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            int[] cur = intervals.get(i);
            if (hasCommon(prev, cur)) {
                prev[0] = Math.min(prev[0], cur[0]);
                prev[1] = Math.max(prev[1], cur[1]);
            } else {
                mergedIntervals.add(cur);
                prev = cur;
            }
        }
        // get result
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < mergedIntervals.size(); i++) {
            int[] cur = mergedIntervals.get(i);
            res.add(cur[1] - cur[0] + 1);
        }
        return res;
    }

    private boolean hasCommon(int[] prev, int[] cur) {
        if (prev[1] >= cur[0] && prev[0] <= cur[1]) {
            return true;
        }
        return false;
    }
}
