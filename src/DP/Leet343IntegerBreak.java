package DP;

public class Leet343IntegerBreak {
        public int integerBreak(int n) {
       /*
       Given an integer,
            find two or more integers such that sum to the integer
            the maximum product of these integers

            DFS
                find all combinations sum to the n
                product --> maximum product

              --> DFS
                    for each level
                            n
                        /   | |   ||||\
                       n-1    n-3 ....
                      /||\
                     1...n-1
                  ...calcuate product
                  compare product with global res

           Time:O(n^n)

           repeated computation
           --> memo
           memo[i]: represents for i, the the maximum product of its combinations
           --> fill the memo array
                Time:O(n^2)

            for (i in range[1, n])
                for (j in range[1, i - 1])
           !!!需要注意memo[2] = 1，但是2本身作为一个因数时应该用2本身，因为2 > memo[2]
       */
            int[] memo = new int[n + 1];
            memo[1] = 1;
            for (int i = 2; i <= n; i++) {
                for (int j = 1; j < i; j++) {
                    memo[i] = Math.max(memo[i], Math.max(j * (i - j), j * memo[i - j]));
                }
            }
            return memo[n];
        }
}
