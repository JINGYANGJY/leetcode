package intuit.meetings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
    check if there are any two intervals has overlap, merge it
    step 1: sort the meetings firstly increasing by start time, then increasing by end time
    step 2: start from index == 1
                check if current has overlap with the last one
                    if true
                        merge
                    else
                        continue
    step 3: get all spare time
 */
public class MergeIntervals {
    public static List<int[]> mergeInterval(int[][] meetings) {
        Arrays.sort(meetings, (m1, m2) -> {
            int res = m1[0] - m2[0];
            if (res == 0) {
                return m1[1] - m2[1];
            }
            return res;
        });
        List<int[]> intervals = new ArrayList<>();
        if (meetings.length == 0) {
            return intervals;
        }
        intervals.add(meetings[0]);
        for (int i = 1; i < meetings.length; i++) {
            int[] cur = meetings[i];
            int[] prev = intervals.get(intervals.size() - 1);
            if (cur[0] > prev[1]) {
                intervals.add(cur);
                continue;
            } else {
                prev[0] = Math.min(cur[0], prev[0]);
                prev[1] = Math.max(cur[1], prev[1]);
               // intervals.set(intervals.size() - 1, new int[] {prev[0], prev[1]});
            }
        }
        List<int[]> res = new ArrayList<>();
        int start = 0;
        for (int[]  meeting : intervals) {
            int end = meeting[0];
            if (start < end) {
                res.add(new int[]{start, end});
            }
            start = meeting[1];
        }
        if (start <= 2400) {
            res.add(new int[]{start, 2400});
        }
        return res;
    }
    public static void main(String[] args) {
        int[][] meetings = { { 1300, 1500 }, { 930, 1300 }, { 830, 845 } };
        for (int[] time : mergeInterval(meetings)) {
            System.out.println("[" + time[0] + ", " + time[1] + "]");
        }
    }
}
