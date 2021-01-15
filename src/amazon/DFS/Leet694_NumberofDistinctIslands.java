package amazon.DFS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leet694_NumberofDistinctIslands {
    public int numDistinctIslands(int[][] grid) {
        /*
        -> graph problem
            distinct connected component
                -> how to represents
                    -> 走向表示 —> <- 下上
          -> find connected component
              represent it using four points
              move leftupper to <0, 0>
              add into Set
              -> distinct number == set.size()
        */
        int n = grid.length;
        int m = grid[0].length;
        Set<List<Integer>> distinctIsland = new HashSet<>();
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    List<Integer> path = new ArrayList<>();
                    dfs(i, j, grid, visited, path, 0);
                    distinctIsland.add(path);
                }
            }
        }
        return distinctIsland.size();
    }
    int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private void dfs(int i, int j, int[][] grid, boolean[][] visited, List<Integer> path, int dir) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[0].length || visited[i][j] || grid[i][j] == 0) {
            return;
        }
        visited[i][j] = true;
        path.add(dir);
        dfs(i + 1, j, grid, visited, path, 1);
        dfs(i - 1, j, grid, visited, path, 2);
        dfs(i, j + 1, grid, visited, path, 3);
        dfs(i, j - 1, grid, visited, path, 4);
        path.add(0);
    }
}
