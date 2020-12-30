package intuit.validMatrix;

import java.util.HashMap;
import java.util.Map;

public class Q1 {
    /*
    给一个N*N的矩阵，判定是否是有效的矩阵。有效矩阵的定义是每一行或者每一列的数字都必须正好是1到N的数。输出一个bool。
input 1：
[[1,3,2],
[2,1,3],
[3,2,1]]
    Map<Integer, int[]> rowMap -- record if it existed each number of each row or column
    Map<Integer, int[]> colMap -- record if it existed each number of each row or column
    <i, j>
    row: i
    col: j
     */
    public static boolean validMatrix(int[][] grid) {
        int N = grid.length;
        Map<Integer, boolean[]> rowMap = new HashMap<>();
        Map<Integer, boolean[]> colMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int num = grid[i][j];
                rowMap.putIfAbsent(i, new boolean[N + 1]);
                colMap.putIfAbsent(j, new boolean[N + 1]);
                if (rowMap.get(i)[num]) {
                    return false;
                }
                if (colMap.get(j)[num]) {
                    return false;
                }
                rowMap.get(i)[num] = true;
                colMap.get(j)[num] = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] grid =
                { {1,3,2},
                  {1,1,3},
                  {1,2,1}
                };
        System.out.println(validMatrix(grid));
    }
}
