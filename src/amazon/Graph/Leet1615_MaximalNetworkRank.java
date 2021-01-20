package amazon.Graph;

import java.util.*;
/*
https://leetcode.com/problems/maximal-network-rank/
 */
public class Leet1615_MaximalNetworkRank {
    public int maximalNetworkRank(int n, int[][] roads) {
        /*
        graph problem
        - construct graph
        - indegree map
            top 2 indgree
        */
        if (roads.length == 0) {
            return 0;
        }
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegreeMap = new HashMap<>();
        Integer largest = null;
        int max = 0;
        Integer second = null;
        int smaller = 0;
        for (int[] road : roads) {
            int v = road[0];
            int w = road[1];
            graph.putIfAbsent(v, new HashSet<>());
            graph.putIfAbsent(w, new HashSet<>());
            graph.get(v).add(w);
            graph.get(w).add(v);
            inDegreeMap.put(v, inDegreeMap.getOrDefault(v, 0) + 1);
            inDegreeMap.put(w, inDegreeMap.getOrDefault(w, 0) + 1);
        }
        int res = 0;
        List<Integer> list = new ArrayList<>(inDegreeMap.keySet());
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (graph.get(list.get(i)).contains(list.get(j))) {
                    res = Math.max(res, inDegreeMap.get(list.get(i)) + inDegreeMap.get(list.get(j)) - 1);
                } else {
                    res = Math.max(res, inDegreeMap.get(list.get(i)) + inDegreeMap.get(list.get(j)));
                }
            }
        }
        return res;
    }
}
