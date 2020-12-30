package intuit.treasure;

public class treasure2 {
    /*
    第二问， 从给定的点出发，能不能到达所有的0
    DFS
        from <i, j>, find all its connected nodes
        and check all 0s has been visited
        find all its connected nodes
           Graph:
                Nodes: all 0s
                edge: four neighbor Nodes
           DFS traverse
           n is the number of rows of grid
           m is the number of cols of grid
           Time: O(4^(n * m))
     */
    public static boolean connected(int i, int j, int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        dfs(i, j, grid, visited);
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < m; b++) {
                if (grid[a][b] == 0 && !visited[a][b]) {
                    return false;
                }
            }
        }
        return true;
    }
    static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static void dfs(int i, int j, int[][] grid, boolean[][] visited) {
        if (i < 0 || j < 0 || i == grid.length || j == grid[0].length || visited[i][j] || grid[i][j] == 1) {
            return;
        }
        visited[i][j] = true;
        for (int[] d : directions) {
            int dx = d[0] + i;
            int dy = d[1] + j;
            dfs(dx, dy, grid, visited);
        }
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] { { 0, -1, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
        System.out.println(connected(0, 0, grid));
    }

}
