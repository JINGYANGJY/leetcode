package amazon.Greedy;

import java.util.PriorityQueue;

public class Leet1648_SellDiminishing_ValuedColoredBalls {
    public int maxProfit(int[] inventory, int orders) {
        int mod = 1000000007;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return a - b < 0 ? 1 : -1;
        });
        for (int i : inventory) {
            pq.offer(i);
        }
        long res = 0;
        int cur = pq.isEmpty() ? 0 :  pq.poll();
        int count = 1;
        while (orders > 0) {
            int next = pq.isEmpty() ? 0 : pq.peek();
            if (count * (cur - next) <= orders) {
                orders -= count * (cur - next);
                long num =  (1L * (next + 1 + cur) * (cur - next) * count / 2) % mod;
                res = (res + num) % mod;

            } else {
                next = cur - (orders / count);
                long num =  (1L * (next + 1 + cur) * (cur - next) * count / 2) % mod;
                res = (res + num) % mod;
                res = (res + 1L * next * (orders % count)) % mod;
                orders = 0;
            }
            if (!pq.isEmpty()) cur = pq.poll();
            count++;
        }
        return (int)res;
    }
}
