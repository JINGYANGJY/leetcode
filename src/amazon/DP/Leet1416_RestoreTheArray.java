package amazon.DP;

public class Leet1416_RestoreTheArray {
    int mod = 1000000007;
    public int numberOfArrays(String s, int k) {
        /*
                             S 1317    k = 2000
                        /       |    \   \
                        1       13   131  1317
                       /|\       /\
                      3 31 317  1 17
                     / \
                     1 17

                     ...
            n is the length of s
            DFS: O(n^n)
            -> reduct redudant calculation
            memo[i][j]: for substring(i, j + 1), the number of possible array
            -> valid: value < k
            -> invalid
            start index
            end index
            dfs(arr, start, end, memo, k)
        */
        int n = s.length();
        int[] memo = new int[n];
        char[] arr = s.toCharArray();
        return dfs(arr, 0, memo, k);
    }

    private int dfs(char[] arr, int start, int[] memo, int k) {
        if (start == arr.length) {
            return 1;
        }
        if (arr[start] == '0') {
            return 0;
        }
        if (memo[start] > 0) {
            return memo[start];
        }
        int res = 0;
        long num = 0;
        for (int i = start; i < arr.length; i++) {
            num = num * 10 + arr[i] - '0';
            if (num > k) break;
            res = (res + dfs(arr, i + 1, memo, k)) % mod;
        }
        memo[start] = res;
        return res;
    }
}
