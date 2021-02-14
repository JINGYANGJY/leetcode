package amazon.sort;

import java.util.*;

public class Leet692_TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        /*
        ❌如果frequency相同， 谁前谁后
        -> top k frequent words
            1. each word frequency Map<String, Integer>
            2. top k -> min heap
        */
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            int res = map.get(a) - map.get(b);
            if (res == 0) {
                return a.compareTo(b) < 0 ? 1 : -1;
            }
            return res;
        });
        for (String key : map.keySet()) {
            if (pq.size() < k) {
                pq.offer(key);
            } else {
                Integer count = map.get(key);
                if (map.get(pq.peek()) < count || (map.get(pq.peek()) == count && pq.peek().compareTo(key) > 0)) {
                    pq.poll();
                    pq.offer(key);
                }
            }
        }
        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.poll());
        }
        Collections.sort(res, (a, b) -> {
            int ans = map.get(a) - map.get(b);
            if (ans == 0) {
                return a.compareTo(b);
            }
            return ans > 0 ? -1 : 1;
        });
        return res;
    }
}
