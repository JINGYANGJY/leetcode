package michaels;

public class Leet172_FactorialTrailingZeroes {
    /*
    Given an integer n, return the number of trailing zeroes in n!.
    Follow up: Could you write a solution that works in logarithmic time complexity?
     */

    public int trailing(int target) {
        /*
        the res of n!
        count how many trailing 0s
        Time: O(n)
        ------------------------------------------
        optimizations:
        10 -> 2
        1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,.......20,......25
                2*2   2*3   2*4                                          5 * 5
        10
        2 * 5 = 10
        -> how many 5s
         */

        int count = 0;
        for (int i = 5; i <= target; i += 5) {
            int cur = i;
            while (cur % 5 == 0) {
                count++;
                cur /= 5;
            }
        }
        return count;
    }

    private int trailing2(int n) {
        int count = 0;
        int factor = 5;
        while (factor <= n) {
            count += n / factor;
            factor *= 5;
        }
        return count;
    }

}
