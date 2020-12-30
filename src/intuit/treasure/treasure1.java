package intuit.treasure;

import java.util.ArrayList;
import java.util.List;

public class treasure1 {
    public static List<int[]> allMoves(int i, int j, int[][] grid) {
        List<int[]> res = new ArrayList<>();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] d : directions) {
            int dx = i + d[0];
            int dy = j + d[1];
            if (dx < 0 || dy < 0 || dx == grid.length || dy == grid[0].length || grid[dx][dy] == 1) {
                continue;
            }
            res.add(new int[]{dx, dy});
        }
        return res;
    }
    public static void main(String[] args) {
        int[][] grid = new int[][] { { 1, 0, 1 }, { 0, 1, 0 }, { 1, 0, 0 } };
        List<int[]> result = allMoves(1, 1, grid);
        for (int[] cell : result) {
            System.out.println(cell[0] + " " + cell[1]);
        }
    }
}
