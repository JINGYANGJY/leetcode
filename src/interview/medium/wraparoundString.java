package interview.medium;

public class wraparoundString {
    public int findSubstringInWraproundString(String p) {
        /*
        dp[i]: represents ending at i, number of unique substrings in wrapround string
        initial: current starting point of substring which is wraparound string
        dp[i] =  i - initial + 1
        len[index]: ending with 'a' + index, the substring's length
                    < 26    currenLen > len[index]     dp[i] += currentLen - len[index] len[index] = currentLen
                    > 26    26 len[index] = currentLen
        res: record the sum of wrapround strings
        res += dp[i];
        */
        int[] record = new int[26];
        int[] dp = new int[p.length()];
        int res = 0;
        int start = 0;
        char[] arr = p.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            int index = arr[i] - 'a';
            int curLen = i == 0 ? 1 : arr[i] > arr[i - 1] && arr[i] - arr[i - 1] == 1 ||
                    arr[i] < arr[i - 1] && arr[i] - arr[i - 1] + 26 == 1 ? i - start + 1 : 1;
            if (i > 0 && (arr[i] - arr[i - 1] != 1 && arr[i] - arr[i - 1] + 26 != 1)) {
                start = i;
            }
            if (record[index] < curLen) {
                dp[i] = curLen - record[index];
                record[index] = curLen;
            }
            res += dp[i];
        }
        return res;
    }
}
