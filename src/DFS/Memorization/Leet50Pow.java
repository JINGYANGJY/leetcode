package DFS.Memorization;

import java.util.HashMap;
import java.util.Map;

public class Leet50Pow {
    /*
        goals to get pow(x, n)
        BF:
            for (1 to n)
                get x * x
        optimizations:
            2^10 == > 2^5 * 2^5
                        |
                       2^2 * 2^2 * 2
                        |
                      2 * 2
            n --> Logn

           memo
           DFS + Memo
                   10
                  / \
                 5
                 /\
                 2
                 /
                1
            n % 2 == 0 ? half(x, n/2) * half(x, n/2)
                          : half(x, n/2) * half(x, n/2) * x
            memo[i] : represents n == i, the res of pow(x, i)
        */
    public double myPow(double x, int n) {
        long m = n;
        if (n < 0) {
            x = 1 / x;
            m = -m;
        }
        if (n == 0) {
            return 1;
        }
        Map<Long, Double> map = new HashMap<>();
        return helper(x, m, map);
    }

    private double helper(double x, long n, Map<Long, Double> map) {
        if (n == 1) {
            return x;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        double half = helper(x, n / 2, map);
        double res = n % 2 == 0 ? half * half : half * half * x;
        map.put(n, res);
        return res;
    }
}
