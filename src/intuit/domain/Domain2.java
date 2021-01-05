package intuit.domain;

import java.util.ArrayList;
import java.util.List;

public class Domain2 {
    /*
    给每个user访问历史记录，找出两个user之间longest continuous common history.
    String[] s1 = { "3234.html", "xys.html", "7hsaa.html" }; // user1
    String[] s2 = { "3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html" }; // user2

    start from any index of s1
            s1 and s2
            for (i in s1)   i's range(0, s1.length)
                for (j in s2) j's range(0, s2.length)
    Time: O(n * m * min(n, m) * O|average length of each domain|)
    n is length of s1
    m .............s2

    optimization:
        dp[i][j]: ending at i of s1 and j of s2, the longest continuous common history.
        dp[i][j]
            s1[i] == s2[j]
                dp[i - 1][j - 1]
            s1[i] != s2[j]
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1])
        dp[0][0] =
            s1[0] == s2[0] ? 1 : 0

            for each step, the start index of the longest continuous common history.
                           the current length of ....
                            if dp[i][j] >= current ....
                                   update start index
                                   current length
      Time: O(n * m * average len of each domain)
     */

    public static List<String> commonHistory(String[] user1, String[] user2) {
        List<String> res = new ArrayList<>();
        int n = user1.length;
        int m = user2.length;
        int[][] dp = new int[n + 1][m + 1];
        dp[0][0] = 0;
        int startIndex =  -1;
        int curLen = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (user1[i- 1].equals(user2[j - 1])) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > curLen) {
                        curLen = dp[i][j];
                        startIndex = i - curLen;
                    }
                }
            }
        }
        for (int i = startIndex; i < startIndex + curLen; i++) {
            res.add(user1[i]);
        }
        return res;
    }
    public static void main(String[] args) {
        String[] s1 = { "3234.html", "xys.html", "7hsaa.html" }; // user1
        String[] s2 = { "3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html" }; // user2
        //List<String> result = commonHistory(s1, s2);
        List<String> result = commonLongest(s1, s2);
        System.out.println("[");
        for (String s : result) {
            System.out.println(s + " ,");
        }
        System.out.println("]");
    }
    public static List<String> commonLongest(String[] s1, String[] s2) {
        int res = 0;
        int n = s1.length;
        int m = s2.length;
        int startIndex = 0;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1[i - 1].equals(s2[j - 1])) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        List<String> result = new ArrayList<>();
        for (int i = startIndex; i <= startIndex + res; i++) {
            result.add(s1[i]);
        }
        return result;

    }


}
