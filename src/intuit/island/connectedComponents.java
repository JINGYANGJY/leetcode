package intuit.island;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class connectedComponents {
    public static List<List<int[]>> connectedComponents(int[][] input) {
        int n = input.length;
        int m = input[0].length;
        boolean[][] visited = new boolean[n][m];
        List<List<int[]>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (input[i][j] == 0 && !visited[i][j]) {
                    List<int[]> partial = new ArrayList<>();
                    dfs(i, j, input, visited, partial);
                    res.add(partial);
                }
            }
        }
        return res;
    }

    private static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static void dfs(int i, int j, int[][] input, boolean[][] visited, List<int[]> partial) {
        if (i < 0 || j < 0 || i == input.length || j == input[0].length || input[i][j] != 0 || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        partial.add(new int[]{i, j});
        for (int[] d : directions) {
            int dx = d[0] + i;
            int dy = d[1] + j;
            dfs(dx, dy, input, visited, partial);
        }
    }

    public static void main(String[] args) {
        int[][] islands = new int[][] {
                {1,1,1,1,1,1},
                {0,0,1,0,1,1},
                {0,0,1,0,1,1},
                {1,1,1,0,0,0},
                {1,0,0,1,1,1}
        };
        List<List<int[]>> rs = connectedComponents(islands);
        for (List<int[]> r : rs) {
            for (int[] corr : r) {
                System.out.println(Arrays.toString(corr));
            }
            System.out.println();
        }
    }

}
