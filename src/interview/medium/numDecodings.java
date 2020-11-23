package interview.medium;

public class numDecodings {
    /*
    A message containing letters from A-Z is being encoded to numbers using the following mapping:
    'A' -> 1
    'B' -> 2
    ...
    'Z' -> 26
    Given a non-empty string containing only digits, determine the total number of ways to decode it.
    The answer is guaranteed to fit in a 32-bit integer.
     */
    public static int numDecodings2(String s) {
        /*
        dp
        dp[i]: represents ending at i, the num of Decoding ways
        dp[i] = dp[i - 1] + dp[i - 2] if (s.charAt(i - 1) - '0' * 1) + s.charAt(i) - '0' >= 10 && <= 26) && i > 1
        dp[0] = s.charAt(0) == 0 ? 0 : 1
        dp[1] = dp[0] + Integer.valueOf(s.substring(0, 1)) >= 10 && <= 26 ? 1 : 0;
        fill order: from left to right
        Time: O(n)
          */
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.charAt(0) == 0) return 0;
        int[] dp = new int[s.length()];
        char[] arr = s.toCharArray();
        dp[0] = arr[0] - '0' == 0 ? 0 : arr[0] - '0';
        int num = 0;
        if (s.length() > 0) {
            num = (arr[0] - '0') * 10 + (arr[1] - '0');
        }
        dp[1] = dp[0] + num >= 10 && num <= 26 ? 1 : 0;
        for (int i = 2; i < arr.length; i++) {
            int n = (arr[i - 1] - '0') * 10 + arr[i] - '0';
            dp[i] = dp[i - 1] + n >= 10 && n <= 26 ? 1 : 0;
        }
        return dp[arr.length - 1];
    }
    public static int numDecodings(String s) {
        /*
        input: string only contains digits
        output: number of ways to decode it

        Assumption:
            string is not null or empty
            can only be interpret to 'A' - 'Z'
        Sol:
            Backtracking
                 12
                /   \
               1    12
               /
              2
              Time; O(2^n)

         */
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        char[] arr = s.toCharArray();
        int[] res = new int[1];
        dfs(arr, 0, res);
        return res[0];
    }
    private static void dfs(char[] arr, int index, int[] res) {
        if (index == arr.length) {
            res[0] += 1;
            return;
        }
        if (arr[index] - '0' > 0) {
            dfs(arr, index + 1, res);
        }
        if (index + 1 < arr.length) {
            int num = (arr[index] - '0') * 10 + arr[index + 1] - '0';
            if (num >= 10 && num <= 26) {
                dfs(arr, index + 2, res);
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(numDecodings("10000"));
    }

}
