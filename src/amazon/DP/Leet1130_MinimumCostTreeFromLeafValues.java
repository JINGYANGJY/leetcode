package amazon.DP;

public class Leet1130_MinimumCostTreeFromLeafValues {
    /*
    https://leetcode.com/problems/minimum-cost-tree-from-leaf-values/
    look discussion, there is a O(N) solution with stack
        public int mctFromLeafValues(int[] A) {
            int res = 0;
            Stack<Integer> stack = new Stack<>();
            stack.push(Integer.MAX_VALUE);
            for (int a : A) {
                while (stack.peek() <= a) {
                    int mid = stack.pop();
                    res += mid * Math.min(stack.peek(), a);
                }
                stack.push(a);
            }
            while (stack.size() > 2) {
                res += stack.pop() * stack.peek();
            }
            return res;
    }
     */
    public int mctFromLeafValues(int[] arr) {
        /*
        among all possible binary tree, return smallest possible sum
        -> DFS
                                arr
                             [6,2,4]
                             /      \
              6 * 2 * dfs(i + 2)   6 * dfs(i + 1)
              int[] memo
              memo[i][j]: represent start from index i, the smallest possible sum
        */
        int n = arr.length;
        int[][] memo = new int[n][n];
        int[][] max = getMaxMatrix(arr);
        return dfs(0, arr.length - 1, arr, memo, max);
    }

    private int[][] getMaxMatrix(int[] arr) {
        int n = arr.length;
        int[][] max = new int[n][n];
        for (int i = 0; i < n; i++) {
            int curMax = arr[i];
            for (int j = i; j < n; j++) {
                curMax = Math.max(curMax, arr[j]);
                max[i][j] = curMax;
            }
        }
        return max;
    }

    private int dfs(int left, int right, int[] arr, int[][] memo, int[][] max) {
        if (left >= right) {
            return 0;
        }
        if (memo[left][right] > 0) {
            return memo[left][right];
        }
        int min = Integer.MAX_VALUE;
        for (int i = left; i < right; i++) {
            min = Math.min(min, max[left][i] * max[i + 1][right] + dfs(left, i, arr, memo, max) +
                    dfs(i + 1, right, arr, memo, max));
        }
        memo[left][right] = min;
        return min;
    }
}
