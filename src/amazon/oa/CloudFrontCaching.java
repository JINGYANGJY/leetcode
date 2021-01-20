package amazon.oa;

import java.util.ArrayList;
import java.util.List;

public class CloudFrontCaching {
    public static int sumOfCost(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int v = edge[0];
            int w = edge[1];
            graph.get(v).add(w);
            graph.get(w).add(v);
        }
        int res = 0;
        boolean[] visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            int[] nodes = new int[1];
            dfs(i, graph, visited, nodes);
            res += Math.ceil(Math.sqrt(nodes[0]));
        }
        return res;
    }

    private static void dfs(int cur, List<List<Integer>> graph, boolean[] visited, int[] nodes) {
        if (visited[cur]) {
            return;
        }
        visited[cur] = true;
        nodes[0] += 1;
        for (Integer i : graph.get(cur)) {
            dfs(i, graph, visited, nodes);
        }
    }
    public static void main(String[] args) {
        int n = 10;
        int[][] edges = {{1, 2}, {1, 3}, {2, 4}, {3, 5}, {7, 8}};
        System.out.println(sumOfCost(n, edges));
    }
}
