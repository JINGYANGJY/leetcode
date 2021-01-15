package intuit.island;

import java.util.*;

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
            {1,1,1,1,1,0},
            {0,0,1,1,1,1},
            {0,0,1,0,0,1},
            {1,1,1,0,0,1},
            {1,0,1,0,0,1}
        };


        System.out.println("------matrix prefix sum find rightBottom coordinate-----------");
        List<int[]> res = allIslands(islands);
        for (int[] r : res) {
            System.out.println(Arrays.toString(r));
        }
        System.out.println("------DFS find rightBottom coordinate-----------");
        List<int[]> r = findRectangle(islands);
        for (int[] c : r) {
            System.out.println(c[0] + "," + c[1]);
        }
        System.out.println("----------Binary search find rightBottom coordinate--------");
        int[] rectangular = findRectangular(islands);
        System.out.println(Arrays.toString(rectangular));

        System.out.println("----------find all rectangular in the matrix--------");
        List<int[]> allRec = findAllIsland(islands);
        for (int[] i : allRec) {
            System.out.println(Arrays.toString(i));
        }

        System.out.println("----------find all rectangular four coordinates in the matrix--------");
        List<List<int[]>> all = findAllIslandOnePass(islands);
        for (List<int[]> l : all) {
            for (int[] i : l) {
                System.out.print(Arrays.toString(i) + " ");
            }
            System.out.println();
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

    public static int[] findRectangular(int[][] islands) {
        /*
            Given an integer matrix, 0 and 1, this matrix contains one rectangular, find it and represent it use its
            leftUpper point and rightBottom point
            1   1   1
            0   0   0
            0   0   0
            1   1   1

            for each cell
                cell is 0
                    <i, j> ---> right<i, j'>
                    |
                    |
                    bottom       <i', j'>
                    <i', j>
            Time: O(n^2)
                <i, j>  == 0
                    find the rightmost 0 of row i
                    find the bottommost o of column j
            Time:O(n^2)
        */
        int n = islands.length;
        int m = islands[0].length;
        int[] res = new int[4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                 if (islands[i][j] == 0) {
                        res[0] = i;
                        res[1] = j;
                        int rightmost = binarySearch(islands, j, m - 1, true, i);
                        int bottommost = binarySearch(islands, i, n - 1, false, j);
                        res[2] = bottommost;
                        res[3] = rightmost;
                        return res;
                 }
            }
        }
        return res;
    }

    private static int binarySearch(int[][] islands, int left, int right, boolean isRow, int rowOrColumn) {
        int res = -1;
        while (left <= right) {
            int mid  = left + (right - left) / 2;
            if (isRow) {
                if (islands[rowOrColumn][mid] == 0) {
                    res = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (islands[mid][rowOrColumn] == 0) {
                    res = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return res;
    }

    /*
    Clarification:
        1. the rectangular are separated or not? yes
        2. if there is only one 0, is it a rectangular?

    find all rectangular in the matrix
            {1,1,1,1,1,1},
            {0,0,1,0,0,1},
            {0,0,1,0,0,1},
            {1,1,1,0,0,0},
            {1,1,1,1,1,1}

            for all 0s <i, j>
                  -> right -> rightmost
                  -> down -> bottommost


     */
    public static List<List<int[]>> findAllIslandOnePass(int[][] grid) {
        // key is id, value are four coordinates, index = 0, leftupper, 1: rightupper, 2: leftbottom, 3 rightbottom
        Map<Integer, List<int[]>> rectangles = new HashMap<>();
        int n = grid.length;
        int m = grid[0].length;
        int[][] rectangleIDs = new int[n + 1][m + 1]; // value is rectangle ids
        int index = 1; // rectangle initial id
        int curIndex = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (grid[i - 1][j - 1] == 0) {
                    if (rectangleIDs[i][j] == 0) {
                        if (rectangleIDs[i - 1][j] == 0 && rectangleIDs[i][j - 1] == 0) {
                            rectangles.putIfAbsent(index, new ArrayList<>());
                            rectangles.get(index).add(new int[]{i - 1, j - 1});
                            rectangleIDs[i][j] = index;
                            index++;
                        } else if (rectangleIDs[i - 1][j] == 0 && rectangleIDs[i][j - 1] != 0) {
                            curIndex = rectangleIDs[i][j - 1];
                            updateRectangles(rectangleIDs, rectangles, i, j, curIndex, 1);
                        } else if (rectangleIDs[i- 1][j] != 0 && rectangleIDs[i][j - 1] == 0) {
                            curIndex = rectangleIDs[i - 1][j];
                            rectangleIDs[i][j] = curIndex;
                            updateRectangles(rectangleIDs, rectangles, i, j, curIndex, 2);
                        } else if (rectangleIDs[i- 1][j] != 0 && rectangleIDs[i][j - 1] != 0) {
                            curIndex = rectangleIDs[i - 1][j];
                            updateRectangles(rectangleIDs, rectangles, i, j, curIndex, 3);
                        }
                    }
                }
            }
        }
        List<List<int[]>> res = new ArrayList<>();
        for (Map.Entry entry : rectangles.entrySet()) {
            res.add((List<int[]>) entry.getValue());
        }
        return res;
    }

    private static void updateRectangles(int[][] rectangleIDs, Map<Integer, List<int[]>> rectangles, int i, int j, int curIndex, int coordinate) {
        rectangleIDs[i][j] = curIndex;
        if (rectangles.get(curIndex).size() == coordinate) {
            rectangles.get(curIndex).add(new int[] {i - 1, j - 1});
        } else {
            rectangles.get(curIndex).set(coordinate, new int[] {i - 1, j - 1});
        }
    }

    public static List<int[]> findAllIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0 && !visited[i][j]) {
                    int rightmost = findRightmost(grid, i, j);
                    int bottommost = findBottommost(grid, i, j);
                    markVisited(grid, visited, i, j, bottommost, rightmost);
                    res.add(new int[]{i, j, bottommost, rightmost});
                }
            }
        }
        return res;
    }

    private static void markVisited(int[][] grid, boolean[][] visited, int i , int j, int bottommost, int rightmost) {
        for (int n = i; n <= bottommost; n++) {
            for (int m = j; m <= rightmost; m++) {
                if (grid[n][m] == 0) {
                    visited[n][m] = true;
                }
            }
        }
    }
    private static int findRightmost(int[][] grid, int i, int j) {
        while (j < grid[0].length && grid[i][j] == 0) {
            j++;
        }
        return j - 1;
    }

    private static int findBottommost(int[][] grid, int i, int j) {
        while (i < grid.length && grid[i][j] == 0) {
            i++;
        }
        return i - 1;
    }

}
