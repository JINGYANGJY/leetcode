package DFS.Memorization;

import java.util.Arrays;

public class Lee292NimGame {
    public boolean canWinNim(int n) {
        /*
        Given n
            goals to find if i can win ..

            1 2 3 stones from n
            1 2 3 stones from the left stone
            --> the one remove last stone is winner

            I firstly
                if take 1
                    F take from n - 1
                if take 2
                    F  ... n - 2
                if take 3
                    F ... n - 3

            DFS
                base case:
                    n == 0 return false
                    n >= 1 && n <= 3
                           return true
            add memo
                int[] memo = new int[n + 1];
                memo[i]: left i stones, who win the name
                if memo[i] == 1, I win
                           == 0, I lose the game
        */
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        return helper(n, memo) == 1;
    }

    private int helper(int n, int[] memo) {
        if (n == 0) {
            return 0;
        }
        if (n >= 1 && n <= 3) {
            return 1;
        }
        if (memo[n] != -1) {
            return memo[n];
        }
        int res = 0;
        //if 条件里是如果剩下的结果是对方输了，那么就是我赢了
        if (helper(n - 1, memo) == 0 || helper(n - 2, memo) == 0 || helper(n - 3, memo) == 0) {
            res = 1;
        }
        memo[n] = res;
        return res;
    }
}
