package amazon.oa;

import java.util.*;

public class TopKFrequent {
    /*
    Given a list of reviews, a list of keywords and an integer k.
    Find the most frequent k keywords in order of most to least frequently mentioned.

    The comparison of strings is case-insensitive.
    Multiple occurrences of a keyword in a review should be considered as a single mention.
    If keywords are mentioned an equal number of times in reviews, sort alphabetically.
     */

    public static List<String> topK(String[] keywords, String[] reviews, int k) {

        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String key : keywords) {
            frequencyMap.putIfAbsent(key, 0);
        }
        for (String review : reviews) {
            String[] str = review.split(" ");
            for (String s : str) {
                if (frequencyMap.containsKey(s)) {
                    frequencyMap.put(s, frequencyMap.get(s) + 1);
                }
            }
        }
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            int res = frequencyMap.get(a) - frequencyMap.get(b);
            if (res == 0) {
                return a.compareTo(b);
            }
            return res  < 0 ? -1 : 1;
        });
        for (String key : frequencyMap.keySet()) {
            if (pq.size() < k) {
                pq.offer(key);
            } else {
                int f1 = frequencyMap.get(pq.peek());
                int f2 = frequencyMap.get(key);
                if (f2 > f1) {
                    pq.poll();
                    pq.offer(key);
                } else if (f2 == f1) {
                    if (pq.peek().compareTo(key) < 0) {
                        pq.poll();
                        pq.offer(key);
                    }
                }
            }

        }
        List<String> res = new ArrayList<>();
        while (k > 0) {
            res.add(pq.poll());
            k--;
        }
        //Collections.sort(res);
        return res;
    }

    public static void main(String[] args) {
        String[] keywords = {"anacell", "betacellular", "cetracular", "deltacellular", "eurocell"};
        String[] reviews = {"I love anacell Best services; Best services provided by anacell",

                "betacellular has great services",

                "deltacellular provides much better services than betacellular",

                "cetracular is worse than anacell",

                "Betacellular is better than deltacellular."};
        int k = 2;
        List<String> res = topK(keywords, reviews, k);
        for (String s : res) {
            System.out.println(s);
        }

    }
}
