package amazon.DP;

import java.text.DecimalFormat;

public class Leet1058_MinimizeRoundingErrortoMeetTarget {
    /*
    TLE
     */
    DecimalFormat df = new DecimalFormat("0.000");
    public String minimizeError(String[] prices, int target) {
        /*
        DFS
                    target
                    pieces
            for each piece, Floor or Ceil

                        /           \
      prices[i]       Floor          Ceil
    target - (Floor)
                    /
pieces[i - 1] Floor     Ceil
        int[][] memo
        memo[i][target]
            start from index i with target, the smallest rounding error
        */

        int n = prices.length;
        String[][] memo = new String[n][target + 1];
        return dfs(0, target, prices,  memo);
    }

    private String dfs(int index, int target, String[] prices, String[][] memo) {
        if (index >= prices.length || target <= 0) {
            if (index == prices.length && target == 0) {
                return "0";
            }
            return "-1";
        }
        if (memo[index][target] != null && memo[index][target].equals("-1")) {
            return memo[index][target];
        }
        // floor
        double price = Double.valueOf(prices[index]);
        double floor = Math.floor(price);
        String ans = dfs(index + 1, target - (int)floor, prices, memo);
        double val1 = ans.equals("-1") ? Double.MAX_VALUE : Double.valueOf(ans) + Math.abs(price - floor);
        //ceil
        double ceil = Math.ceil(price);
        ans = dfs(index + 1, target - (int)ceil, prices, memo);
        double val2 = ans.equals("-1") ? Double.MAX_VALUE : Double.valueOf(ans)  +  Math.abs(price - ceil);

        double res = Math.min(val1, val2);
        if (res == Double.MAX_VALUE) {
            memo[index][target] = "-1";
        } else {
            memo[index][target] = df.format(res);
        }
        return memo[index][target];
    }
}
