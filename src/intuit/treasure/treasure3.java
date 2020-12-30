package intuit.treasure;

import java.util.ArrayList;
import java.util.List;

public class treasure3 {
    /*
    第三问，现在矩阵里多了一种元素1，矩阵由 -1， 0， 1 构成，
    问给定一个起始点，再给定一个结束点，找到最短的从起始点到结束点的路径，这个路径必须覆盖所有的1。 DFS + 回溯可以做
    DFS
                    start       i, j
                        /   |       |    \
                      left  right  up   down
                      /||\

                      find all paths contains all 1, and has shortest length

              int pathLen -- current path's length
              int[] max -- the shortest path
              List<List<int[]>> -- list of paths
              int[] numberOf1
              int numOf1
         Time: O(4^n*m)
     */

    public static List<List<int[]>> shortestPath(int[][] grid, int[] start, int[] end) {
        List<List<int[]>> paths = new ArrayList<>();
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        int[] longest = new int[1];
        longest[0] = Integer.MAX_VALUE;
        int numOfTreasure = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    numOfTreasure++;
                }
            }
        }
        List<int[]> path = new ArrayList<>();
        path.add(start);
        dfs(start, path, grid[start[0]][start[1]] == 1 ? 1 : 0, longest, visited, numOfTreasure, end, grid, paths);
        return paths;
    }
    static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static void dfs(int[] cur, List<int[]> path, int treasures, int[] longest, boolean[][] visited, int numOfTreasure, int[] end, int[][] grid, List<List<int[]>> paths) {
        if (cur[0] == end[0] && cur[1] == end[1] && treasures == numOfTreasure) {
            if (path.size() < longest[0]) {
                paths.removeAll(paths);
                longest[0] = path.size();
                paths.add(new ArrayList<>(path));
            } else if (path.size() == longest[0]) {
                paths.add(new ArrayList<>(path));
            }
        }
        for (int[] d : directions) {
            int dx = d[0] + cur[0];
            int dy = d[1] + cur[1];
            if (dx < 0 || dy < 0 || dx == grid.length || dy == grid[0].length || visited[dx][dy] || grid[dx][dy] == -1) {
                continue;
            }
            visited[dx][dy] = true;
            path.add(new int[]{dx, dy});
            dfs(new int[]{dx, dy}, path, grid[dx][dy] == 1 ?  treasures + 1 : treasures , longest, visited, numOfTreasure, end, grid, paths);
            visited[dx][dy] = false;
            path.remove(path.size() - 1);
        }
    }
    public static void main(String[] args) {
        // Test case1:
        // treasure(board3, (5, 0), (0, 4)) -> None

        // Test case2:
        // treasure(board3, (5, 2), (2, 0)) ->
        // [(5, 2), (5, 1), (4, 1), (3, 1), (3, 2), (2, 2), (2, 3), (1, 3), (0, 3), (0,
        // 2), (0, 1), (0, 0), (1, 0), (2, 0)]
        // Or
        // [(5, 2), (5, 1), (4, 1), (3, 1), (3, 2), (3, 3), (2, 3), (1, 3), (0, 3), (0,
        // 2), (0, 1), (0, 0), (1, 0), (2, 0)]

        // Test case3:
        // treasure(board3, (0, 0), (4, 1)) ->
        // [(0, 0), (0, 1), (0, 2), (0, 3), (1, 3), (2, 3), (2, 2), (3, 2), (3, 1), (4,
        // 1)]
        // Or
        // [(0, 0), (0, 1), (0, 2), (0, 3), (1, 3), (2, 3), (3, 3), (3, 2), (3, 1), (4,
        // 1)]
        int[][] grid = new int[][]
                {       { 1, 0, 0, 0, 0 },
                        { 0, -1, -1, 0, 0 },
                        { 0, -1, 0, 1, 0 },
                        { -1, 0, 0, 0, 0 },
                        { 0, 1, -1, 0, 0 },
                        { 0, 0, 0, 0, 0 } };
        List<List<int[]>> paths = shortestPath(grid, new int[] { 0, 0 }, new int[] { 4, 1 });

        for (List<int[]> path : paths) {
            System.out.println("Path:[");
            for (int[] p : path) {
                System.out.println("[" + p[0] + " ," + p[1] + "]");
            }
            System.out.println("]");
        }
    }
}
