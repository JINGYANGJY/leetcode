package amazon.Array;

public class Leet926_FlipStringtoMonotoneIncreasing {
    public int minFlipsMonoIncr(String S) {
        /*
        longest increasing subsequence
        dp[i]: ends with index i, the longest increasing subsequence
        dp[i] = arr[i] == 0
                    max over { dp[j] }  if arr[j] = 0
                arr[i] == 1
                    max over { dp[j] }  if arr[j] = 1
        Time: O(n^2)
        ---------------------------------------------------------------
        optimization
        arr[i] = 0 or 1
        int ends0
        int ends1
        if (arr[i] == 0)
            ends0 += 1
        if (arr[i] == 1)
            ends1 = Math.max(ends0, ends1) + 1
        */

        char[] arr = S.toCharArray();
        int ends0 = 0;
        int ends1 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '0') {
                ends0 = ends0 + 1;
            } else {
                ends1 = Math.max(ends0, ends1) + 1;
            }
        }
        return arr.length - Math.max(ends0, ends1);
    }
}
