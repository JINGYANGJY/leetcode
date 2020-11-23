package interview.medium;

import java.util.ArrayList;
import java.util.List;

public class spiralOrder {
    /*
    Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
    1   2   3
    4   5   6
    7   8   9
    10  11  12

    1 2 3 6 9 12 11 10 7 4 5 8
    input: matrix n * m
    output: List<Integer> spiral order
    <i, j> represents matrix[i][j]
    n: matrix.length
    m: matrix[0].length
        right: ->       (i, j++) until j == m - 1     dir change: i = i + 1, j = j
        down: |         (i++, j) until i == n - 1     dir change: i = i, j = j - 1
        left: <-        (i, j--) until j == 0(minC)    dir change: i = i - 1, j = j
        up: |           (i--, j) until i == 1(minR)    dir change: i = i, j = j + 1
     this is one round
        next round start position (1, 1) m = m - 2, n = n - 2
     */

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) {
            return res;
        }
        dfs(matrix, 0, 0 , matrix.length, matrix[0].length, res);
        return res;
    }

    private static void dfs(int[][] matrix, int i, int j, int n, int m, List<Integer> res) {
        if (i == n || m == j || n == 0 || m == 0) {
            /*
            {1, 2, 3, 4}
            {5, 6, 7, 8} i == n || m == j 避免6 重复被加
             */
            return;
        }
        int row = i, col = j;
        for (; col < m; col++) {
            res.add(matrix[row][col]);
        }
        row += 1;
        col -= 1;
        for (; row < n; row++) {
            res.add(matrix[row][col]);
        }
        row -= 1;
        col -= 1;
        for (;col >= j && row > i; col--) {
            res.add(matrix[row][col]);
            /*
            避免
            2， 6， 9
            中6 2被重复加
             */
        }
        col += 1;
        row -= 1;
        for (; row > i && col < m - 1; row--) {
            res.add(matrix[row][col]);
            /*
            避免
            [2]
            [6]
            [8]
            col < m - 1 中6被重复加
             */
        }
        dfs(matrix, i + 1, j + 1, n - 1, m - 1, res);
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {2, 5, 8 },
                {4, 0, -1},
        };
        List<Integer> res = spiralOrder(matrix);
        for (Integer i : res) {
            System.out.println(i);
        }

    }

}
