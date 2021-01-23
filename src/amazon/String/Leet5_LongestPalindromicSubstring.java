package amazon.String;

public class Leet5_LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        /*
        dp[i][j]: the longest palindromic substring within [i, j]
        if (arr[j] == arr[i])
            dp[i][j] = dp[i + 1][j - 1] + 2
        base case:
            i == j
                dp[i][j] = 1;
            i + 1 == j
                dp[i][j] = arr[j] == arr[i] ? 2 : 1;
        filling order
                            <i, j>
         <i + 1, j - 1>
         from left to right
         from bottom to top
        */
        int n = s.length();
        int[][] dp = new int[n][n];
        char[] arr = s.toCharArray();
        int startIndex = -1;
        int len = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = i; j < arr.length; j++) {
                if (i == j) {
                    dp[i][j] = 1;
                } else if (i + 1 == j) {
                    dp[i][j] = arr[i] == arr[j] ? 2 : 1;
                } else if (arr[i] == arr[j] && dp[i + 1][j - 1] == j - i - 1) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }
                if (dp[i][j] > len) {
                    len = dp[i][j];
                    startIndex = i;
                }
            }
        }
        return s.substring(startIndex, startIndex + len);
    }
}
