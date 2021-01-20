package amazon.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Leet1326_MinimumNumberofTapstoOpentoWateraGarden {
    /*
    https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/
    写法有些复杂，但是是目前想到的方法，有待改善
     */
    class Interval {
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public int minTaps(int n, int[] ranges) {
        /*
        Given an integer n and an array ranges, ranges[i] can water the area[i - range[i], i + range[i]].
        return the minimal number of taps to cover [0, n]

        -> each ranges[i] is an interval [i - range[i], i + range[i]]
        -> return the minimal number of interval to cover 0 - n
           -> [start, end]
           -> find minimal number of intervals merged which can cover 0 - n
           -> sort intervals by end
            dp[i]: represents ends with current interval, the minimal number of intervals who cover [0, n]
            dp[i] =
                    min over {dp[j]} + 1    interval[j].end >= interval[i].start - 1
        */
        List<Interval> intervals = new ArrayList<>();
        for (int i = 0; i < ranges.length; i++) {
            intervals.add(new Interval(i - ranges[i], i + ranges[i]));
        }
        Collections.sort(intervals, (a, b) -> {
            int res = a.end - b.end;
            if (res == 0) {
                return a.start - b.start < 0 ? -1 : 1;
            }
            return res;
        });
        int[] dp = new int[intervals.size()];
        int res = Integer.MAX_VALUE;
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0; i < intervals.size(); i++) {
            int index = lastIntervalIndex(intervals, dp, i - 1, intervals.get(i).start);
            if (intervals.get(i).start <= 0) {
                dp[i] = Math.min(dp[i], 1);
            } else if (index != -1 && dp[index] != Integer.MAX_VALUE) {
                dp[i] = Math.min(dp[i], dp[index] + 1);
            }
            if (intervals.get(i).end >= n) {
                res = Math.min(res, dp[i]);
            }
        }
        // System.out.println(Arrays.toString(dp));
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private int lastIntervalIndex(List<Interval> intervals, int[] dp, int end, int target) {
        int res = Integer.MAX_VALUE;
        int standard = Integer.MAX_VALUE;
        for (int i = end; i >= 0; i--) {
            if (intervals.get(i).end >= target) {
                if (dp[i] < standard) {
                    standard = dp[i];
                    res = Math.min(res, i);
                }
            } else {
                break;
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
