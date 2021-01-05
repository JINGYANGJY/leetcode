package intuit.island;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class island1 {
    static class Coordinate {
        public int col;
        public int row;
        public Coordinate(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    //先是一个01矩阵里找一个矩形的岛的坐标
    // Time: O(n^2) Space: O(n^2)
    public static List<int[]> allIslands(int[][] islands) {
        int n = islands.length;
        int m = islands[0].length;
        Coordinate[][] coordinates = new Coordinate[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (islands[i][j] == 1) {
                    coordinates[i][j] = new Coordinate(0, 0);
                    continue;
                }
                if (i == 0 || j == 0) {
                    if (i == 0 && j == 0 && islands[i][j] == 0) {
                        coordinates[i][j] = new Coordinate(1, 1);
                    } else if (i == 0 && islands[i][j] == 0) {
                        coordinates[i][j] = new Coordinate(1, coordinates[i][j - 1].col + 1);
                    } else if (j == 0 && islands[i][j] == 0) {
                        coordinates[i][j] = new Coordinate(coordinates[i - 1][j].row + 1, 1);
                    }
                } else if (islands[i][j] == 0) {
                    coordinates[i][j] = new Coordinate(coordinates[i - 1][j].row + 1, coordinates[i][j - 1].col + 1);
                }
            }
        }
        List<int[]> res = new ArrayList<>();
        boolean[][] visited = new boolean[n][m];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int height = coordinates[i][j].row;
                int width = coordinates[i][j].col;
                if ((height > 0 || width > 0 )&& !visited[i - height + 1][j - width + 1]) {
                    res.add(new int[]{i - height + 1, j - width + 1, i, j});
                    visited[i - height + 1][j - width + 1] = true;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] islands = new int[][] {
            {1,1,1,1,1,1},
            {0,0,1,0,1,1},
            {0,0,1,0,1,1},
            {1,1,1,0,0,0},
            {1,0,0,1,1,1}
        };
        List<int[]> res = allIslands(islands);
        for (int[] r : res) {
            System.out.println(Arrays.toString(r));
        }
        System.out.println("-----------------");
        List<int[]> r = findRectangle(islands);
        for (int[] c : r) {
            System.out.println(c[0] + "," + c[1]);
        }
    }

    public static List<int[]> findRectangle(int[][] input) {
        int n = input.length;
        int m = input[0].length;
        List<int[]> res = new ArrayList<>();
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (input[i][j] == 0 && !visited[i][j]) {
                    res.add(new int[]{i, j});
                    int[] cor = new int[2];
                    dfs(i, j, input, visited, cor);
                    res.add(cor);
                }
            }
        }
        return res;
    }

    private static int[][] directions = {{1, 0}, {0, 1}};
    private static void dfs(int i, int j, int[][] input, boolean[][] visited, int[] res) {
        if (i < 0 || j < 0 || i == input.length || j == input[0].length || input[i][j] != 0 || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        res[0] = Math.max(res[0], i);
        res[1] = Math.max(res[1], j);
        for (int[] d : directions) {
            int dx = d[0] + i;
            int dy = d[1] + j;
            dfs(dx, dy, input, visited, res);
        }
    }

}
