package amazon.Graph;

public class Leet547_NumberofProvinces {
    public int findCircleNum(int[][] isConnected) {
        /*
        non-directed graph problem
            -> number of components
            -> DFS
                if same component mark them
        */
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, isConnected, visited);
                res++;
            }
        }
        return res;
    }

    private void dfs(int cur, int[][] isConnected, boolean[] visited) {
        if (visited[cur]) {
            return;
        }
        visited[cur] = true;
        for (int i = 0; i < isConnected.length; i++) {
            if (cur != i && isConnected[cur][i] == 1) {
                dfs(i, isConnected, visited);
            }
        }
    }
}
