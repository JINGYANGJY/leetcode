package amazon.Greedy;

public class Leet861_ScoreAfterFlippingMatrix {
    public int matrixScore(int[][] A) {
        /*
        To make the sum of these numbers larger
            For each row, make leftmost to 1
            for each column, make more 1
        */
        int n = A.length;
        int m = A[0].length;
        for (int i = 0; i < n; i++) {
            if (A[i][0] == 0) {
                flipRow(i, A);
            }
        }
        // System.out.println(Arrays.deepToString(A));
        for (int i = 0; i < m; i++) {
            if (countOnes(i, A) < (n % 2 == 0 ? n / 2 : (n / 2) + 1)) {
                flipCol(i, A);
            }
        }
        // System.out.println(Arrays.deepToString(A));
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += rowVal(i, A);
        }
        return res;
    }
    private int rowVal(int row, int[][] A) {
        int res = 0;
        int base = 1;
        for (int i = A[row].length - 1; i >= 0 ; i--) {
            res += base * A[row][i];
            base *= 2;
        }
        return res;
    }
    private int countOnes(int col, int[][] A) {
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i][col] == 1) {
                res++;
            }
        }
        return res;
    }
    private void flipCol(int col, int[][] A) {
        for (int i = 0; i < A.length; i++) {
            A[i][col] = A[i][col] == 0 ? 1 : 0;
        }
    }
    private void flipRow(int row, int[][] A) {
        for (int i = 0; i < A[row].length; i++) {
            A[row][i] = A[row][i] == 0 ? 1 : 0;
        }
    }
}
