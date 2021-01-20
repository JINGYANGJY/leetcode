package amazon.oa;

import java.util.PriorityQueue;

public class PartsAssembly {
    public static int minCost(int[] parts) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : parts) {
            pq.offer(i);
        }
        int res = 0;
        while (pq.size() > 1) {
            int curCost = pq.poll() + pq.poll();
            res += curCost;
            pq.offer(curCost);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] parts = {8, 4, 6, 12};
        System.out.println(minCost(parts));
    }
}
