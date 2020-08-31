package interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Calrification:
 * interval[0] <= interval[1]
 *
 * merge intervals[i] if intervals[i] has overlapping with others
 *
 * 1. sort intervals by start
 * 2. check if intervals[i] has overlapping with the last one of the result list
 * 		if true, merge intervals[i] and res[j], update res[j]
 * 		else add intervals[i] to res
 *
 * Time: O(nlogn + n)
 *
 *
 * ⚠️ list to array
 * list<int[]> res = new ArrayList<>();
 * res.toArray(new int[res.size()][2]);
 */

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        for (int i = 0; i < intervals.length; i++) {
            if (res.size() == 0 || res.get(res.size() - 1)[1] < intervals[i][0]) {
                res.add(intervals[i]);
            } else {
                int[] last = res.get(res.size() - 1);
                last[1] = Math.max(last[1], intervals[i][1]);
            }
        }
        return res.toArray(new int[res.size()][2]);
    }
}
