package weeklyContest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
https://leetcode.com/problems/minimum-operations-to-make-a-subsequence/
 */
public class Leet1713_MinimumOperationstoMakeaSubsequence {
    public int minOperations(int[] target, int[] arr) {
        /*
        -> find the longest subsequence of target and arr
        dp[i][j]: represents ends with index i - 1 of target arr, and ends with index j - 1 of arr
        the longest subsequence of target and arr
        dp[i][j] = {
            if target[i - 1] == arr[i - 1]
                dp[i - 1][j - 1]  + 1
            otherwise
                Math.max(dp[i - 1][j], dp[i][j - 1])
        }
        dp[0][0] = 0;
        int n = target.length;
        int m = arr.length;
        int res = 0;
        int[][] dp = new int[2][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (target[i - 1] == arr[j - 1]) {
                    dp[i % 2][j] = dp[(i - 1) % 2][j - 1] + 1;
                } else {
                    dp[i % 2][j] = Math.max(dp[(i - 1) % 2][j], dp[i % 2][j - 1]);
                }
                res = Math.max(res, dp[i % 2][j]);
            }
        }
        return target.length - res;
        */
        /*
        distinct integers in the target
        --> reverse index in arr
        find longest increasing index in the arr
        */
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            map.put(target[i], i);
        }
        List<Integer> list = new ArrayList<>();
        for (int i : arr) {
            if (map.containsKey(i)) {
                list.add(map.get(i));
            }
        }
        List<Integer> lenIndex = new ArrayList<>();
        // [4,7,6,2,3,8,6,1]
        // 1.  0 5 4 2 0 3
        int res = 0;
        for (int i = 0; i < list.size(); i++) {
            int cur = list.get(i);
            if (lenIndex.size() == 0) {
                lenIndex.add(cur);
                continue;
            }
            // find the largest smaller value's index than cur
            int index = binarySearch(lenIndex, cur);
            if (index == -1 || index < lenIndex.size() - 1) {
                int min = Math.min(lenIndex.get(index + 1), cur);
                lenIndex.set(index + 1, cur);
            } else {
                lenIndex.add(cur);
            }
        }
        return target.length - lenIndex.size();
    }

    private int binarySearch(List<Integer> list, int target) {
        int res = -1;
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) < target) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }
}
