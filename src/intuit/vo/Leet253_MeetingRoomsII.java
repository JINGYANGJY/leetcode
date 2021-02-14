package intuit.vo;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Leet253_MeetingRoomsII {
    /*
    https://leetcode.com/problems/meeting-rooms-ii/
     */
    public int minMeetingRooms(int[][] intervals) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];
        });
        int res = 0;
        for (int[] interval : intervals) {
            if (!pq.isEmpty() && pq.peek()[1] <= interval[0]) {
                pq.poll();
            }
            pq.offer(interval);
            res = Math.max(res, pq.size());
        }
        return res;
    }
}
