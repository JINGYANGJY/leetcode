package DP.MINMAX;

import java.util.Arrays;

public class Leet1552MagneticForceBetweenTwoBalls {
    // M1 Time limited
    public int maxDistance(int[] position, int m) {
        /*
            DFS
            [1,2,3,4,7]


                  1                      p       np
                                        / \
                  2                   p    np
                                     /\
                                    p np
                until m == 0
                Enumerate all put ways, find the minimum magnetic force between any two balls is maximum.
            Time: O(2^n)
            Actually find m number from positions so that the minimum |x - y| of any two number is maximum
            no need to sort the positions firstly
            dp[i][j]:
                ends at index j, put i-th balls at j, the minimum |x - y|
            dp[i][j]:
                max over(min over(dp[i - 1][k],  |position[j] -  position[k]| k is in range[i - 1, j - 1]))
            dp[0][j] = Integer.MAX_VALUE
        */
        Arrays.sort(position);
        int[][] dp = new int[m][position.length];
        for (int i = 0; i < position.length; i++) {
            dp[0][i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i < m; i++) {
            for (int j = i; j < position.length; j++) {
                int max = 0;
                for (int k = i - 1; k < j; k++) {
                    max = Math.max(max, Math.min(dp[i - 1][k], Math.abs(position[j] - position[k])));
                }
                dp[i][j] = max;
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[m - 1][position.length - 1];
    }
    /*
    M2 Binary search
    result must between min diff and max diff
    */
    public int maxDistanceBinarySearch(int[] position, int m) {
        int min = position[0];
        int max = position[1];
        for (int i : position) {
            min = Math.min(min, i);
            max = Math.max(max, i);
        }
        Arrays.sort(position);
        int left = 1;
        int right = max - min;
        int res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canArrange(position, mid, m)) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    private boolean canArrange(int[] position, int target, int m) {
        int res = 0;
        int slow = 0;
        for (int i = 1; i < position.length; i++) {
            if (position[i] - position[slow] >= target) {
                slow = i;
                m--;
            }
        }
        return m <= 1;
    }
}
