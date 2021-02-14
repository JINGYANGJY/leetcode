package oa;

import java.util.List;
import java.util.PriorityQueue;

public class Akamai {
    public static int lastStoneWeight(List<Integer> weight) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> {
            return b - a;
        });
        pq.addAll(weight);
        int res = 0;
        while (pq.size() >= 2) {
            int first = pq.poll();
            int second = pq.poll();
            int diff = Math.abs(first - second);
            if (diff > 0) {
                pq.offer(diff);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }
}
