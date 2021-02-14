package amazon.DFS;

public class Leet200_NumberofIslands {
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    dfs(i, j, visited, grid);
                    res++;
                }
            }
        }
        return res;
    }
    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private void dfs(int i, int j, boolean[][] visited, char[][] grid) {
        if (i < 0 || j < 0 || i == grid.length || j == grid[0].length || visited[i][j] || grid[i][j] == '0') {
            return;
        }
        visited[i][j] = true;
        for (int[] dir : directions) {
            int dx = dir[0] + i;
            int dy = dir[1] + j;
            dfs(dx, dy, visited, grid);
        }
    }
}
