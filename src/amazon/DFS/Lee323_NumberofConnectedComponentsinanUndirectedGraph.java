package amazon.DFS;

import java.util.ArrayList;
import java.util.List;

public class Lee323_NumberofConnectedComponentsinanUndirectedGraph {
    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> graph = constructGraph(n, edges);
        boolean[] visited = new boolean[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                res++;
                dfs(i, graph, visited);
            }
        }
        return res;
    }

    private void dfs(int cur, List<List<Integer>> graph, boolean[] visited) {
        if (visited[cur]) {
            return;
        }
        visited[cur] = true;
        for (Integer nei : graph.get(cur)) {
            dfs(nei, graph, visited);
        }
    }

    private List<List<Integer>> constructGraph(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }
}
