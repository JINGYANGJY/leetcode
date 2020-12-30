package intuit.island;

import java.util.ArrayList;
import java.util.List;

public class island2 {
    //先是一个01矩阵里找一个矩形的岛的坐标
    public static List<int[]> findRectangle(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0 && !visited[i][j]) {
                    int[] coordinates = new int[4];
                    coordinates[0] = i;
                    coordinates[1] = j;
                    int[] leftBottom = new int[2];
                    dfs(i, j, grid, visited, leftBottom);
                    coordinates[2] = leftBottom[0];
                    coordinates[3] = leftBottom[1];
                    res.add(coordinates);
                }
            }
        }
        return res;
    }

    private static void dfs(int i, int j, int[][] grid, boolean[][] visited, int[] leftBottom) {
        if (i < 0 || j < 0 || i == grid.length || j == grid[0].length || visited[i][j] || grid[i][j] == 1) {
            return;
        }
        visited[i][j] = true;
        leftBottom[0] = Math.max(leftBottom[0], i);
        leftBottom[1] = Math.max(leftBottom[1], j);
        dfs(i + 1, j, grid, visited, leftBottom);
        dfs(i, j + 1, grid, visited, leftBottom);
    }

    public static void main(String[] args) {
        int[][] grid =  {
                {1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0},
        };
        List<int[]> res = findRectangle(grid);
        for (int[] r : res) {
            for (int i : r) {
                System.out.print( i + ", ");
            }
            System.out.println();
        }

    }
}
