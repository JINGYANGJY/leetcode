package amazon.DP;

import java.util.Arrays;

public class Leet1092_ShortestCommonSupersequence {
    public String shortestCommonSupersequence(String str1, String str2) {
        /*
        step 1: find the longest common subsequence
                -> dp[i][j]: represents the the longest common subsequence within [i, j]
        step 2: 合并
        */
        int n = str1.length();
        int m = str2.length();
        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        String[][] dp = new String[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], "");
        }
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + arr1[i - 1] ;
                } else {
                    dp[i][j] = dp[i - 1][j].length() > dp[i][j - 1].length() ?  dp[i - 1][j] : dp[i][j - 1];
                }
            }
        }
        String subsequence = dp[n][m];
        char[] arr = subsequence.toCharArray();
        StringBuilder res = new StringBuilder();
        int p1 = 0;
        int p2 = 0;
        for (int index = 0; index < arr.length; index++) {
            while (p1 < arr1.length && arr1[p1] != arr[index]) {
                res.append(String.valueOf(arr1[p1++]));
            }
            while (p2 < arr2.length && arr2[p2] != arr[index]) {
                res.append(String.valueOf(arr2[p2++]));
            }
            res.append(String.valueOf(arr[index]));
            p1++;
            p2++;
        }
        res.append(str1.substring(p1));
        res.append(str2.substring(p2));
        return res.toString();
    }
}
