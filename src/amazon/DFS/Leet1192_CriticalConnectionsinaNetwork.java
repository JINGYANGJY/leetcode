package amazon.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leet1192_CriticalConnectionsinaNetwork {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        /*
        if an edge is one edge of a circle, it won't be a critival connection
        -> find all the edges which are not in the circle
        -> find circle?
            -> DFS
                  graph problem
                    -> construct graph undirected connected graph
                    -> if start from v -> w get a return dfs(w) < rank(v)
                        -> there is a circle, v-> w is not a critical connection
                        -> otherwise, add into result
                    -> rank(v)
                        -> if there is no circle, rank is increasing by 1
                        -> dfs(cur), return value is the rank of the node who has been visited before
                        -> visited == rank
        */
        List<List<Integer>> graph = constructGraph(n, connections);
        int[] rank = new int[n];
        Arrays.fill(rank, Integer.MAX_VALUE);
        List<List<Integer>> res = new ArrayList<>();
        dfs(-1, 0, 0, rank, graph, res);
        return res;
    }

    private int dfs(int prev, int cur,int curRank, int[] rank, List<List<Integer>> graph, List<List<Integer>> res) {
        if (rank[cur] != Integer.MAX_VALUE) {
            return rank[cur];
        }
        rank[cur] = curRank;
        int minDepthFound = curRank;
        for (Integer nei : graph.get(cur)) {
            if (nei == prev || rank[nei] == rank.length) continue;
            int depth = dfs(cur, nei, curRank + 1, rank, graph, res);
            minDepthFound = Math.min(minDepthFound, depth);
            if (depth > curRank) {
                res.add(Arrays.asList(cur, nei));
            }
        }
        rank[cur] = rank.length;//completely visited
        return minDepthFound;
    }

    private List<List<Integer>> constructGraph(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> edge : connections) {
            int v = edge.get(0);
            int w = edge.get(1);
            graph.get(v).add(w);
            graph.get(w).add(v);
        }
        return graph;
    }
}
