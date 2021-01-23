package amazon.String;

public class Leet1216_ValidPalindromeIII {
    public boolean isValidPalindrome(String s, int k) {
        /*
        find the longest panlindrome subsequence
        dp[i][j] represents the longest valid palindrome subsequence of range [i, j]
        dp[i][j]:
            if (arr[i] == arr[j])
                dp[i][j] = dp[i + 1][j - 1] + 2;
            else
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
        base case:
            dp[i][i] = 1;
            dp[i][i + 1] = arr[i] == arr[i + 1] ? 2 : 1;
        Time: O(n^2)
        Space: O(n^2)
        */
        int longest = 1;
        int n = s.length();
        int[][] dp = new int[n][n];
        char[] arr = s.toCharArray();
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = i; j < arr.length; j++) {
                if (i == j) {
                    dp[i][j] = 1;
                } else if (i + 1 == j) {
                    dp[i][j] = arr[i] == arr[j] ? 2 : 1;
                } else {
                    if (arr[i] == arr[j]) {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    }
                }
                longest = Math.max(longest, dp[i][j]);
            }
        }
        return arr.length - longest <= k;
    }
}
