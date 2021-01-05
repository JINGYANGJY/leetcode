package DFS.Memorization;

import java.util.Arrays;

public class Leet464CanIWin {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        /*
        Game
            rule:
                the first one reach the desiredTotal wins the game
                -- can not reuse the integer between 1 - maxChoosableInteger

            DFS:
                for my turn, and the left ChoosableInteger i can choose to reach desiredTotal
                i win
                otherwise i loose the game

                boolean[] choosableInteger;
                int val

                base case:
                    if (desiredTotal == 0) {
                        return false; // i lose
                    }
                    if (desiredTotal - i == 0) {
                        i win
                    }

        */
        int sum =(1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if(sum < desiredTotal) {
            return false;
        }
        if (desiredTotal <= maxChoosableInteger) {
            return true;
        }
        int[] memo = new int[1 << (maxChoosableInteger + 1)];
        Arrays.fill(memo, -1);
        return dfs(desiredTotal, 0, maxChoosableInteger, memo) == 1;
    }
    // 0: I lose
    // 1; I win
    private int dfs(int desiredTotal, int state, int maxChoosableInteger, int[] memo) {
        if (desiredTotal <= 0) {
            return 0;
        }
        if (memo[state] != -1) {
            return memo[state];
        }
        int res = 0;
        for (int i = 1; i <= maxChoosableInteger; i++) {
            if ((state & (1 << i)) == 0) {
                if (dfs(desiredTotal - i, state | (1 << i), maxChoosableInteger, memo) == 0) {
                    res = 1;
                    memo[state] = 1;//当我在当前状态调用dfs时，我得到的值。因此并不是memo[state | (1 << i)] = 1
                    return res;
                }
            }
        }
        memo[state] = res;
        return res;
    }
}
