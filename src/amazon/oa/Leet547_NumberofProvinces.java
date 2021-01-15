package amazon.oa;

import java.util.ArrayList;
import java.util.List;

public class Leet547_NumberofProvinces {
    public int findCircleNum(int[][] isConnected) {
        /*
        graph problem
        undirected graph
        find how many comoonents in the graph
        DFS -> tranverse the graph
               start from any unmarked node, mark all reachable nodes
               -> start times = number of components
        */
        int res = 0;
        int n = isConnected.length;
        List<List<Integer>> graph = constructGraph(isConnected);
        boolean[] visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
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
    private List<List<Integer>> constructGraph(int[][] isConnected) {
        List<List<Integer>> graph = new ArrayList<>();
        int n = isConnected.length;
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    graph.get(i + 1).add(j + 1);
                    graph.get(j + 1).add(i + 1);
                }
            }
        }
        return graph;
    }
}
