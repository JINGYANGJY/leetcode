package amazon.DP;

import java.util.Arrays;

public class Leet650_2KeysKeyboard {
    public int minSteps(int n) {
        /*
        -> return the minimum number of steps to get n 'A'
           at most n steps
           -> recursion
                n
                minimum number of steps to decrease number of 'A' to 1
                                 n
                      /      /      \       ｜  ...
                    CP      CPP     CPPP    CPPPP
                    int[] memo
                    memo[i]: represent the minimum steps to decrease number of 'A' to 1
        */
        if (n == 1) return 0;
        int[] memo = new int[n + 1];
        Arrays.fill(memo, Integer.MAX_VALUE);
        return dfs(n, memo);
    }

    private int dfs(int n, int[] memo) {
        if (n <= 4) {
            memo[n] = n;
            return n;
        }
        if (memo[n] != Integer.MAX_VALUE) {
            return memo[n];
        }
        int min = n;
        for (int i = 2; i < n / 2; i++) {
            if (n % i == 0 && n / i >= 2) {
                min = Math.min(min, dfs(i, memo) + (n / i));
            }
        }
        memo[n] = min;
        return min;
    }
}
