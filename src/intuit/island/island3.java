package intuit.island;

import java.util.ArrayList;
import java.util.List;

public class island3 {
    public static List<List<int[]>> allRectangles(int[][] grid) {
        List<List<int[]>> res = new ArrayList<>();
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0 && !visited[i][j]) {
                    List<int[]> boardlines = new ArrayList<>();
                    for (int k = 0; k < 4; k++) {
                        boardlines.add(new int[]{i, j});
                    }
                    dfs(i, j, grid, visited, boardlines);
                    res.add(boardlines);
                }
            }
        }
        return res;
    }
    static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    //List<int[]> boardlines includes leftUpper leftBottom rightUpper rightBottom coordinates
    private static void dfs(int i, int j, int[][] grid, boolean[][] visited, List<int[]> boardlines) {
        if (i < 0 || j < 0 || i == grid.length || j == grid[0].length || visited[i][j] || grid[i][j] == 1) {
            return;
        }
        // leftUpper{i, j}
        visited[i][j] = true;
        updateBoardlines(boardlines, i, j);
        for (int[] d : directions) {
            int dx = d[0] + i;
            int dy = d[1] + j;
            dfs(dx, dy, grid, visited, boardlines);
        }

    }

    private static void updateBoardlines(List<int[]> boardlines, int i, int j) {
        int[] leftUpper = boardlines.get(0);
        leftUpper[0] = Math.min(leftUpper[0], i);
        leftUpper[1] = Math.min(leftUpper[1], j);
        int[] rightUpper = boardlines.get(1);
        rightUpper[0] = Math.min(rightUpper[0], i);
        rightUpper[1] = Math.max(rightUpper[1], j);
        int[] leftBottom = boardlines.get(2);
        leftBottom[0] = Math.max(leftBottom[0], i);
        leftBottom[1] = Math.min(leftBottom[1], j);
        int[] rightBottom = boardlines.get(3);
        rightBottom[0] = Math.max(rightBottom[0], i);
        rightBottom[1] = Math.max(rightBottom[1], j);
    }

    public static void main(String[] args) {
        int[][] grid =  {
                {1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0},
                {1, 0, 0, 1, 0},
        };
        List<List<int[]>> res = allRectangles(grid);
        for (List<int[]> list : res) {
            for (int[] r : list) {
                for (int i : r) {
                    System.out.print( i + ", ");
                }
            }
            System.out.println();
        }

    }
}
