package amazon.Array;

public class Leet59_SpiralMatrixII {
    public int[][] generateMatrix(int n) {
        /*
            3
        1   2   3   4
        12  13 14   5
 3      11  16 15   6     3
        10  9   8   7
                3

       start len = n - 1
             then len -= 2
        start coordinate<0, 0>
                        <1, 1>
                         <2, 2>
                         ....
            end condition:
                num == n * n
            the innerest has two cases
                1. len = 0
                2. len = 1
            four direction
            towards right
                    down
                    left
                    up
        */
        int[][] res = new int[n][n];
        int num = 1;
        int initialX = 0;
        int initialY = 0;
        int len = n - 1;
        while (num <= n * n && len >= 0) {
            int x = initialX;
            int y = initialY;
            if (len == 0) {
                res[x][y] = num;
                break;
            }
            // right
            for (int i = 0; i < len; i++) {
                res[x][y] = num;
                y++;
                num++;
            }
            //down
            for (int i = 0; i < len; i++) {
                res[x][y] = num;
                x++;
                num++;
            }
            //left
            for (int i = 0; i < len; i++) {
                res[x][y] = num;
                y--;
                num++;
            }
            //up
            for (int i = 0; i < len; i++) {
                res[x][y] = num;
                x--;
                num++;
            }
            initialX += 1;
            initialY += 1;
            len -= 2;
        }
        return res;
    }
}
