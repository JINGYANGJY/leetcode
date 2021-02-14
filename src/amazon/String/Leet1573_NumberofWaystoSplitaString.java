package amazon.String;

public class Leet1573_NumberofWaystoSplitaString {
    public int numWays(String s) {
        /*
        split into 3 non-empty strings
        for each part number of 1 should be equals
        step 1:
            # of 1s
            -> each part how many 1s
        step 2:
            start from index = 0
                find the lefmost and rightmost of left middle and right
            1 0 1 0 1
            left [0, 0]
            middle[2, 2]
            right[4, 4]
            2 * 2 = 4

            "0000"
            all are 0s
            C3(2)
        */
        long mod = 1000000007;
        char[] arr = s.toCharArray();
        int num = 0;
        for (char c : arr) {
            if (c == '1') {
                num++;
            }
        }
        int res = 0;
        long len = s.length();
        if (num == 0) {
            return (int) ((len - 1) * (len - 2) / 2 % mod);
        }
        if (num % 3 != 0 || len < 3) {
            return 0;
        }
        long numOfZeroOfPart1 = 0;
        int numOfOnes = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '1' && numOfOnes < num / 3) {
                numOfOnes++;
            } else if (arr[i] == '0' && numOfOnes == num / 3) {
                numOfZeroOfPart1++;
            } else if (arr[i] == '1' && numOfOnes == num / 3) {
                break;
            }
        }
        numOfOnes = 0;
        long numOfZeroOfPart2 = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == '1' && numOfOnes < num / 3) {
                numOfOnes++;
            } else if (arr[i] == '0' && numOfOnes == num / 3) {
                numOfZeroOfPart2++;
            } else if (arr[i] == '1' && numOfOnes == num / 3) {
                break;
            }
        }
        return (int) (++numOfZeroOfPart1 * ++numOfZeroOfPart2 % mod) ;
    }
}
