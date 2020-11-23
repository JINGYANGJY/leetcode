package interview.easy;

public class fibonacci {
    public int fib(int N) {
    /*
    a == 0, b == 1
        n - 2   n - 1    n     n + 1
          a       b    a + b
                 newa   newb
         newa = b = 0 * a + 1 * b
         newb = a + b = 1 * a + 1 * b

         0  1   *     a         =       newa
         1  1         b                 newb
         循环
            0 1 ^ N  * a
            1 1        b
         fast exponatiation
         0 1 ^ N
         1 1

    */
        int[][] prod = {{1, 0}, {0, 1}};
        int[][] base = {{0, 1}, {1, 1}};
        while (N > 0) {
            if (N % 2 == 1) prod = multiple(prod, base);
            base = multiple(base, base);
            N >>= 1;
        }
        int newa = prod[0][0] * 0 + prod[0][1] * 1;
        int newb = prod[1][0] * 0 + prod[1][1] * 1;
        return N % 2 == 1 ? newb : newa;
    }

    private int[][] multiple(int[][] a, int[][] b) {
        int[][] res = new int[2][2];
        res[0][0] = a[0][0] * b[0][0] + a[0][1] * b[1][0];
        res[0][1] = a[0][0] * b[0][1] + a[0][1] * b[1][1];
        res[1][0] = a[1][0] * b[0][0] + a[1][1] * b[1][0];
        res[1][1] = a[1][0] * b[0][1] + a[1][1] * b[1][1];
        return res;
    }
}
