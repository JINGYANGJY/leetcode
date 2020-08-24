package amazon.oa;

import java.util.Arrays;
import java.util.List;

public class components {
    /** Description:
     * calculate connected components
     * directions: upper, down, left, right
     * Assumptions:
     *      all strings in input has same length
    **/
    public static void main(String[] args) {
        List<String> input = Arrays.asList("aabba", "aabba", "aaacb");
        int numOfRows = 3;
        System.out.println(components(input, numOfRows));

    }

    private static int components(List<String> input, int numOfRows) {
        int numOfColumns = input.get(0).length();
        boolean[][] visited = new boolean[numOfRows][numOfColumns];
        int res = 0;
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                if (!visited[i][j]) {
                    dfs(i, j, input, visited, numOfRows, numOfColumns);
                    res++;
                }
            }
        }
        return res;
    }
    static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static void dfs (int x, int y, List<String> input, boolean[][] visited, int numOfRows, int numOfColumns) {
        visited[x][y] = true;
        for (int[] d : directions) {
            int dx = x + d[0];
            int dy = y + d[1];
            if (dx < 0 || dy < 0 || dx == numOfRows || dy == numOfColumns || visited[dx][dy]) {
                continue;
            }
            if (input.get(dx).charAt(dy) != input.get(x).charAt(y)) {
                continue;
            }
            dfs(dx, dy, input, visited, numOfRows, numOfColumns);
        }
    }
}
