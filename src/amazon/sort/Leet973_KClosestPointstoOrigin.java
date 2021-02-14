package amazon.sort;

import java.util.PriorityQueue;

public class Leet973_KClosestPointstoOrigin {
    public int[][] kClosest(int[][] points, int K) {
        /*
        k closest points
            M1:
                all points distance
                sort -> get K closest
            M2:
                max Heap
                    if heap.size < K
                        add
                    else
                        compare with the peek
                        replace?
        */
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            int dis1 = getDis(a);
            int dis2 = getDis(b);
            return dis1 - dis2 < 0 ? 1 : -1;
        });
        for (int i = 0; i < points.length; i++) {
            if (pq.size() < K) {
                pq.offer(points[i]);
            } else {
                int dis = getDis(points[i]);
                if (getDis(pq.peek()) >= dis) {
                    pq.poll();
                    pq.offer(points[i]);
                }
            }
        }
        int[][] res = new int[K][2];
        int index = 0;
        while (!pq.isEmpty()) {
            res[index++] = pq.poll();
        }
        return res;
    }

    private int getDis(int[] p) {
        return p[0] * p[0] + p[1] * p[1];
    }
}
