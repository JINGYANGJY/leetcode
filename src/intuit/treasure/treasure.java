package intuit.treasure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class treasure {
    /*
                    /   |   |   \
                  left right up down
                  <i, j>
                  Time:O(1)

     */
    //3'13
    private static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static List<int[]> allMoves(int i, int j, int[][] grid) {
        List<int[]> res = new ArrayList<>();
        for (int[] d : directions) {
            int dx = d[0] + i;
            int dy = d[1] + j;
            if (dx < 0 || dy < 0 || dx == grid.length || dy == grid[0].length) {
                continue;
            }
            res.add(new int[]{dx, dy});
        }
        return res;
    }
    /*
      第二问， 从给定的点出发，能不能到达所有的0
      <i, j>
                     <i, j>
                     / | | \

           all reachable cells, all 0s were covered
           <i, j>
           all 0s cell are vertex
           edges:
                for one - cell, right left, up, down

           start current point, traverse the connected component of the graph
           check if all os have been covered
           Time: O(|V| + |E|)  = (m * n + 4 * m * n) -- O(m * n)
     */

    public static boolean treasure2(int i, int j, int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        dfs(i, j, grid, visited);
        for (int a = 0; a < n; a++) {
            for (int b = 0; b < m; b++) {
                if (grid[a][b] == 0) {
                    if (!visited[a][b]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    private static void dfs(int i, int j, int[][] grid, boolean[][] visited) {
        if (i < 0 || j < 0 || i == grid.length || j == grid[0].length || visited[i][j] || grid[i][j] != 0) {
            return;
        }
        visited[i][j] = true;
        for (int[] d : directions) {
            int dx = d[0] + i;
            int dy = d[1] + j;
            dfs(dx, dy, grid, visited);
        }
    }
    /*
    第三问，现在矩阵里多了一种元素1，矩阵由 -1， 0， 1 构成，
    问给定一个起始点，再给定一个结束点，找到最短的从起始点到结束点的路径，这个路径必须覆盖所有的1
                    start   end
                    shortest path 1s
        Clarify:
            return all the shortest path from the start to the end?
            if there isn't a  path which can cover all 1s, what should return?
            1s
            start or end == 1
            yes

            all paths
            -1 0 1
            -1: can not reach
            0: can reach
            1: must cover
            ----------------------------------------------
            graph
            vertex: all cells of 0 and 1
            edges: 0 - 0
                   1
                                         start
                                   /     |  |   \
                                  0


            generate all paths
                        compare path length with current shortest length
                                if > shortest path
                                   <  replace
                                   =  add into result
                        cover
                            how many are there in the grid


           Time: O(|V| ^ |E|)
           讲： 7'
     */
    public static List<List<int[]>> allShortestPath(int[][] grid, int[] start, int[] end) {
        List<List<int[]>> res = new ArrayList<>();
        int[] shortestLen = new int[1];
        shortestLen[0] = Integer.MAX_VALUE;
        int numOfOne = 0;
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    numOfOne++;
                }
            }
        }
        boolean[][] visited = new boolean[n][m];
        List<int[]> curPath = new ArrayList<>();
        curPath.add(start);
        visited[start[0]][start[1]] = true;
        dfs(start, numOfOne, curPath, grid, visited, end, shortestLen, res);
        return res;
    }

    private static void dfs(int[] start, int numOfOne, List<int[]> curPath, int[][] grid, boolean[][] visited, int[] end, int[] shortestLen, List<List<int[]>> res) {
        if (start[0] == end[0] && start[1] == end[1]) {
            if (numOfOne == 0) {
                if (curPath.size() == shortestLen[0]) {
                    res.add(new ArrayList<>(curPath));
                } else if (curPath.size() < shortestLen[0]) {
                    shortestLen[0] = curPath.size();
                    res.clear();
                    res.add(new ArrayList<>(curPath));
                }
            }
            return;
        }
        for (int[] d : directions) {
            int dx = d[0] + start[0];
            int dy = d[1] + start[1];
            if (dx < 0 || dy < 0 || dx == grid.length || dy == grid[0].length || visited[dx][dy] || grid[dx][dy] == -1) {
                continue;
            }
            visited[dx][dy] = true;
            curPath.add(new int[]{dx, dy});
            dfs(new int[]{dx, dy}, grid[dx][dy] == 1 ? numOfOne - 1 : numOfOne,  curPath, grid, visited, end, shortestLen, res);
            visited[dx][dy] = false;
            curPath.remove(curPath.size() - 1);
        }
    }




    public static void main(String[] args) {
        System.out.println("-----------------------------treasure1---------------------------------");
        int[][] grid = new int[][] { { 1, 0, 1 }, { 0, 1, 0 }, { 1, 0, 0 } };
        List<int[]> result = allMoves(1, 1, grid);
        for (int[] cell : result) {
            System.out.println(Arrays.toString(cell));
        }
        System.out.println("-----------------------------treasure2---------------------------------");
        int[][] grid2 = new int[][] { { 0, -1, 0 }, { 0, -1, 0 }, { 0, 0, 0 } };
        System.out.println(treasure2(0, 0, grid2));
        System.out.println("-----------------------------treasure3---------------------------------");
        int[][] grid3 = new int[][]
                {       { 1, 0, 0, 0, 0 },
                        { 0, -1, -1, 0, 0 },
                        { 0, -1, 1, -1, 0 },
                        { -1, 0, -1, 0, 0 },
                        { 0, 1, -1, 0, 0 },
                        { 0, 0, 0, 0, 0 } };
        List<List<int[]>> paths = allShortestPath(grid3, new int[] { 5, 2}, new int[] { 2, 0 });

        for (List<int[]> path : paths) {
            System.out.println("Path:[");
            for (int[] p : path) {
                System.out.println("[" + p[0] + " ," + p[1] + "]");
            }
            System.out.println("]");
        }
    }
}
