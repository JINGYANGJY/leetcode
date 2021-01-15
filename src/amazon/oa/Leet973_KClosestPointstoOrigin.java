package amazon.oa;

import java.util.PriorityQueue;

public class Leet973_KClosestPointstoOrigin {
    public int[][] kClosest(int[][] points, int K) {
       /*
       nlogn
        sort
       nlogk
        heap sort
       */
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            int dis1 = getDistance(a);
            int dis2 = getDistance(b);
            return dis1 - dis2 < 0 ? 1 : -1;
        });
        for (int i = 0; i < points.length; i++) {
            if (pq.size() < K) {
                pq.offer(points[i]);
            } else {
                int cur = getDistance(points[i]);
                int peek = getDistance(pq.peek());
                if (cur < peek) {
                    pq.poll();
                    pq.offer(points[i]);
                }
            }
        }
        int[][] res = new int[K][];
        int index = 0;
        while (!pq.isEmpty()) {
            res[index++] = pq.poll();
        }
        return res;
    }

    private int getDistance(int[] a) {
        return a[0] * a[0] + a[1] * a[1];
    }
}
